package com.tc.swarm.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {

    public static int getDateDiff(String carWashDate) throws ParseException {
        Date todaysDate = new Date();
            //2020-07-20 18:33:16
        Date washtTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(carWashDate);
        System.out.println("washTime: "+washtTime);
        System.out.println("todaysDate: "+todaysDate);

        int diffInDays = (int) (( todaysDate.getTime() - washtTime.getTime()) / (1000 * 60 * 60 * 24));
        System.out.println("diffInDays:"+ diffInDays);
        return diffInDays;
    }
}
