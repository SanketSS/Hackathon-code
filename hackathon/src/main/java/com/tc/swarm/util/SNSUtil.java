package com.tc.swarm.util;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.tc.swarm.model.SnsData;

public class SNSUtil {

    public static void sendSNS(SnsData data){
        AmazonSNS client = AmazonSNSClientBuilder.standard().build();
        String dataString = "https://carwash-alpha.s3.amazonaws.com/index.html?"+"&lat=" + data.getLatitude()+"&long=" +data.getLongitude()+"&model=" + data.getCarModel()+"&lastWashDate="+data.getLastCarWashDate()+"&weather="+data.getWeatherCondition();
        client.publish("arn:aws:sns:us-east-1:077324389754:car-wash-alerts", dataString,"Car Wash Notification");
    }
}
