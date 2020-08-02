package com.tc.swarm.util;

import com.tc.swarm.model.WeatherData;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WeatherUtil {

    public static WeatherData getWeatherCondition(String zip) throws FileNotFoundException {
        List<WeatherData> weatherData = loadData();
        WeatherData weatherConditionData = null;
        for(WeatherData data: weatherData){
            if (data.getZipCode().equalsIgnoreCase(zip)){
                weatherConditionData = data;
            }
        }
        return weatherConditionData;
    }


    private static List<WeatherData> loadData() throws FileNotFoundException {
        Scanner input = new Scanner(new File("weather.txt"));
        input.useDelimiter(",|\n");

        List<WeatherData> weatherData = new ArrayList<>();
        while(input.hasNext()) {
            String zipCode = input.next();
            String condition =  input.next();
            WeatherData newWeatherData = new WeatherData(zipCode,condition);
            weatherData.add(newWeatherData);
        }
        return weatherData;
    }
}
