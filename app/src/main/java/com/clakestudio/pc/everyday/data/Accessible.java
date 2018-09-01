package com.clakestudio.pc.everyday.data;

import java.util.List;

/**
 * Created by Jan on 9/1/2018.
 */

public interface Accessible {

    void getAccessDays(List<Day> dayList);
    void getAccessDaysById(List<Day> dayList);


}
