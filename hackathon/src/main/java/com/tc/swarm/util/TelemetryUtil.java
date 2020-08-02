package com.tc.swarm.util;

import com.tc.swarm.model.TelemetryData;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TelemetryUtil {

    public static TelemetryData getLatestLocation(String vin) throws FileNotFoundException {
        List<TelemetryData> telemetryData = loadData();
        TelemetryData locationData = null;
        for(TelemetryData data: telemetryData){
            if (data.getVin().equalsIgnoreCase(vin)){
                locationData = data;
            }
        }
        return locationData;
    }

    private static List<TelemetryData> loadData() throws FileNotFoundException {
        Scanner input = new Scanner(new File("telemetry.txt"));
        input.useDelimiter(",|\n");

        List<TelemetryData> locationData = new ArrayList<>();
        while(input.hasNext()) {
            String vinNo = input.next();
            String latitude =  input.next();
            String longitude = input.next();
            String zipCode = input.next();
            TelemetryData newTelemetryData= new TelemetryData(vinNo, latitude, longitude,zipCode);
            locationData.add(newTelemetryData);
        }
        return locationData;
    }

}
