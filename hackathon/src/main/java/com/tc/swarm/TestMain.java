package com.tc.swarm;

import com.google.gson.JsonSyntaxException;
import com.tc.swarm.model.SnsData;
import com.tc.swarm.model.TelemetryData;
import com.tc.swarm.model.VinMetricsData;
import com.tc.swarm.model.WeatherData;
import com.tc.swarm.util.RDSUtil;
import com.tc.swarm.util.SNSUtil;
import com.tc.swarm.util.TelemetryUtil;
import com.tc.swarm.util.WeatherUtil;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TestMain {

    public static void main(String[] args) throws FileNotFoundException, ParseException {

        Date todaysDate = new Date();
        try
        {
            //get the data from vin metrics table
            List<VinMetricsData> VinMetricsDataRecords = RDSUtil.loadData();

            //check if car wash is needed, get latest location and weather condition and send sns
            for(VinMetricsData dataRecords:VinMetricsDataRecords ){
                Date washtTime = new SimpleDateFormat("MM/dd/yy HH:mm").parse(dataRecords.getGpsDateTime());
                int diffInDays = (int) (( todaysDate.getTime() - washtTime.getTime()) / (1000 * 60 * 60 * 24));
                if(diffInDays > 7){
                    TelemetryData telemetryData = TelemetryUtil.getLatestLocation(dataRecords.getVin());
                    //get weather condition
                    WeatherData weatherData = WeatherUtil.getWeatherCondition(telemetryData.getZipCode());
                    if (weatherData.getCondition().equalsIgnoreCase("sunny")){
                        /*SnsData snsData = new SnsData(dataRecords.getVin(),telemetryData.getLatitude(),telemetryData.getLongitude(),dataRecords.getCarModel());
                        SNSUtil.sendSNS(snsData);*/
                        System.out.println("send sns for vin:" + dataRecords.getVin() );
                    }
                }
            }

        }
        catch (IllegalStateException | JsonSyntaxException | NullPointerException | ParseException exception )
        {
            System.out.println(exception.getStackTrace());
        }

    }
}
