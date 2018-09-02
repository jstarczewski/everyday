package com.clakestudio.pc.everyday.adddays;

/**
 * Created by Jan on 9/3/2018.
 */

public class DaysDataFormatter {

    public static String[] getDayInfo(String dayInfo, String title, String note) {

        String[] dayInfoArray = new String[4];
        dayInfoArray[0] = String.valueOf(dayInfo.charAt(4));
        dayInfoArray[1] = dayInfo.substring(dayInfo.indexOf('/'), dayInfo.length()-1);
        dayInfoArray[2] = title;
        dayInfoArray[3] = note;
        return dayInfoArray;
    }

}
