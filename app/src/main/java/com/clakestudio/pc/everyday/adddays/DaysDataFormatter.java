package com.clakestudio.pc.everyday.adddays;

/**
 * Created by Jan on 9/3/2018.
 */

class DaysDataFormatter {

    static String[] getDayInfo(String dayInfo, String title, String note) {

        String[] dayInfoArray = new String[4];
        dayInfoArray[0] = String.valueOf(dayInfo.charAt(4));
        dayInfoArray[1] = dayInfo.substring(dayInfo.indexOf("/")+2, dayInfo.length());
        dayInfoArray[2] = title;
        dayInfoArray[3] = note;
        return dayInfoArray;
    }

}
