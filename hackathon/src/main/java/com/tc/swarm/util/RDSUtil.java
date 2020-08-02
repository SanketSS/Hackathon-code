package com.tc.swarm.util;

import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagement;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagementClientBuilder;
import com.amazonaws.services.simplesystemsmanagement.model.GetParameterRequest;
import com.tc.swarm.model.VinMetricsData;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RDSUtil {

    public static List<VinMetricsData> loadRemoteData() throws FileNotFoundException {
        List<VinMetricsData> vinMetricsDataList = new ArrayList<>();
        if (System.getenv("RDS_HOSTNAME") != null) {
            try {
                Class.forName("org.postgresql.Driver");
                String dbName = System.getenv("RDS_DB_NAME");
                //String userName = System.getenv("RDS_USERNAME");
                String userName = getParameter("swarm-db-user") ;
                //String password = System.getenv("RDS_PASSWORD");
                String password = getParameter("swarm-db-pwd");
                String hostname = System.getenv("RDS_HOSTNAME");
                String port = System.getenv("RDS_PORT");
                String jdbcUrl = "jdbc:postgresql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;
                //System.out.println("Getting remote connection with connection string from environment variables.");
                Connection con = DriverManager.getConnection(jdbcUrl);
                //System.out.println("Remote connection successful.");
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM vehicle_wash.vehicle_wash_metrics");

                while (rs.next())
                {
                    VinMetricsData vinMetricsData = new VinMetricsData();
                    vinMetricsData.setVin(rs.getString(1));
                    vinMetricsData.setGpsDateTime(rs.getString(2));
                    vinMetricsData.setLatitude(rs.getString(3));
                    vinMetricsData.setLongitude(rs.getString(4));
                    vinMetricsData.setOdo(rs.getString(5));
                    vinMetricsData.setZipCode(rs.getString(6));
                    vinMetricsData.setLastModified(rs.getString(7));
                    vinMetricsData.setCarModel(rs.getString(8));
                    vinMetricsData.setWashID(rs.getString(9));
                    vinMetricsDataList.add(vinMetricsData);
                }
                rs.close();
                st.close();

                /*for(VinMetricsData data :vinMetricsDataList){
                    System.out.println("remote data:");
                    System.out.println(data.getVin());
                    System.out.println(data.getOdo());
                    System.out.println(data.getZipCode());
                    System.out.println(data.getGpsDateTime());
                    System.out.println("end remote data:");
                }*/

            }
            catch (ClassNotFoundException | SQLException e)
            {
                loadData();
                System.out.println(e.toString());
            }
        }
        return vinMetricsDataList;
    }

    /**
     * Helper method to retrieve SSM Parameter's value
     * @param parameterName identifier of the SSM Parameter
     * @return decrypted parameter value
     */
    public static String getParameter(String parameterName) {
        AWSSimpleSystemsManagement ssm = AWSSimpleSystemsManagementClientBuilder.defaultClient();
        GetParameterRequest request = new GetParameterRequest();
        request.setName(parameterName);
        request.setWithDecryption(true);
        return ssm.getParameter(request).getParameter().getValue();
    }

    public static List<VinMetricsData> loadData() throws FileNotFoundException {

        loadRemoteData();

        Scanner input = new Scanner(new File("vinmetricsdata.txt"));
        input.useDelimiter(",|\n");

        List<VinMetricsData> vinMetricsDataRecords = new ArrayList<>();
        while(input.hasNext()) {
             String vin = input.next();
             String gpsDateTime = input.next();
             String latitude =  input.next();
             String longitude = input.next();
             String odo = input.next();
             String zipCode = input.next();
             String lastModified = input.next();
             String carModel =input.next();
             String washID = input.next();

             VinMetricsData newVinMetricsData = new VinMetricsData(vin, gpsDateTime, latitude, longitude, odo,zipCode,lastModified,carModel,washID);
             vinMetricsDataRecords.add(newVinMetricsData);
        }
     return vinMetricsDataRecords;
    }
}
