package com.chirag.mvvmdemolivedata.viewmodel;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Patterns;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.chirag.mvvmdemolivedata.model.User;


public class LoginViewNewModel extends ViewModel {
    private MutableLiveData<String>  errorPassword = new MutableLiveData<>();
    private MutableLiveData<String> errorEmail = new MutableLiveData<>();
    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<Integer> busy;
    private MutableLiveData<User> userMutableLiveData;
    private User user;

    public MutableLiveData<Integer> getBusy() {

        if (busy == null) {
            busy = new MutableLiveData<>();
            busy.setValue(8);
        }

        return busy;
    }




    public LoginViewNewModel() {
        email.setValue("chirag@gmail.com");
        password.setValue("12345678");
    }



    public LiveData<User> getUser() {
        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }

        return userMutableLiveData;
    }

    public LiveData<String> getError(){
        if (errorEmail == null) {
            errorEmail = new MutableLiveData<>();
        }

        return errorEmail;
    }


    public void onLoginClicked() {

        getBusy().setValue(0); //View.VISIBLE
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isInputDataValid()) {
                    User user = new User(email.getValue(), password.getValue());
                    userMutableLiveData.setValue(user);
                    getBusy().setValue(8);
                    //setToastMessage(successMessage);
                }else {
                    errorEmail.setValue("Email And Passowrd is Invalid");
                    getBusy().setValue(8);
                    //setToastMessage(errorEmail);


                }


            }
        }, 3000);
    }

    public boolean isInputDataValid() {
        return !TextUtils.isEmpty(email.getValue()) && Patterns.EMAIL_ADDRESS.matcher(email.getValue()).matches() && password.getValue().length() > 5;
    }







}
