package com.clakestudio.pc.everyday.password;

import com.clakestudio.pc.everyday.BasePresenter;
import com.clakestudio.pc.everyday.BaseView;

/**
 * Created by Jan on 9/6/2018.
 */

public interface PasswordContract {

    interface View extends BaseView<Presenter> {

        void showShowDaysActivity();

        void showForgotPasswordActivity();

        void showWrongPasswordToast();


    }

    interface Presenter extends BasePresenter {

        boolean isPasswordCorrect(String password);

    }



}
