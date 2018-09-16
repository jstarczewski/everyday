package com.clakestudio.pc.everyday;

/**
 * Created by Jan on 8/30/2018.
 */

public interface BaseView<T> {

    void setPresenter(T presenter);

    void stop();

}
