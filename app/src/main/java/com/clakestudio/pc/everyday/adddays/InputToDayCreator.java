package com.clakestudio.pc.everyday.adddays;

import com.clakestudio.pc.everyday.data.Day;

/**
 * Created by Jan on 9/3/2018.
 */

class InputToDayCreator {

    static Day create(String dayInfo, String title, String note) {

        String[] dayInfoArray = new String[4];

        /**
         * Day bug spotted -> not char at 4, because it can be day 11, 12
         * */

        dayInfoArray[0] = String.valueOf(dayInfo.charAt(4));
        dayInfoArray[1] = dayInfo.substring(dayInfo.indexOf("/")+2, dayInfo.length());
        dayInfoArray[2] = title;
        dayInfoArray[3] = note;
        return new Day(dayInfoArray[0], dayInfoArray[1], dayInfoArray[2], dayInfoArray[3]);
    }

}
