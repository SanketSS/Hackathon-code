package com.tc.swarm;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.tc.swarm.model.SnsData;
import com.tc.swarm.model.TelemetryData;
import com.tc.swarm.model.VinMetricsData;
import com.tc.swarm.model.WeatherData;
import com.tc.swarm.util.CommonUtil;
import com.tc.swarm.util.RDSUtil;
import com.tc.swarm.util.SNSUtil;
import com.tc.swarm.util.TelemetryUtil;
import com.tc.swarm.util.WeatherUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.List;

public class VerifyLambda implements RequestStreamHandler {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException
    {
        LambdaLogger logger = context.getLogger();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("US-ASCII")));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream, Charset.forName("US-ASCII"))));
        try
        {
            //get the data from vin metrics table
            List<VinMetricsData> VinMetricsDataRecords = RDSUtil.loadRemoteData();
            //check if car wash is needed, get latest location and weather condition and send sns
            for(VinMetricsData dataRecords:VinMetricsDataRecords ){
                //check if x number of days have passed from last car wash
                if(CommonUtil.getDateDiff(dataRecords.getGpsDateTime()) > 7){
                    TelemetryData telemetryData = TelemetryUtil.getLatestLocation(dataRecords.getVin());
                    //get weather condition
                    WeatherData weatherData = WeatherUtil.getWeatherCondition(telemetryData.getZipCode());
                    if (weatherData.getCondition().equalsIgnoreCase("sunny")){
                        //send SNS
                        SnsData snsData = new SnsData(telemetryData.getLatitude(),telemetryData.getLongitude(),dataRecords.getCarModel(), dataRecords.getGpsDateTime(),weatherData.getCondition());
                        SNSUtil.sendSNS(snsData);
                    }
                }
            }
        }
        catch (IllegalStateException | JsonSyntaxException | NullPointerException | ParseException exception )
        {
            logger.log(exception.toString());
        }
        finally
        {
            reader.close();
            writer.close();
        }
    }

}
