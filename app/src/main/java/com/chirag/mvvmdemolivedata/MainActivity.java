package com.chirag.mvvmdemolivedata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.widget.Toast;

import com.chirag.mvvmdemolivedata.databinding.ActivityMainBinding;
import com.chirag.mvvmdemolivedata.model.User;
import com.chirag.mvvmdemolivedata.viewmodel.LoginViewNewModel;

public class MainActivity extends AppCompatActivity {
    public String url = "http://testingapi.osm-erp.com/SyncMaster/GetSyncDetail1/companyId/5515d6e7-42cf-4993-806c-3a7cc510712d/languageId/cbd3788b-08df-46a0-b5b6-11522aac8caa/businessUnitId/93941f03-ea7a-44e1-945f-0716af3decc0/userId/eddfd0c4-8e71-440c-b417-b14abb6fdc5d/lastSyncDate/2000-01-01/transactionName/SyncAllTables/synctype/All";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        LoginViewNewModel loginViewModel =  new LoginViewNewModel();
        activityMainBinding.setViewModel(loginViewModel);
        activityMainBinding.setLifecycleOwner(this);
        //activityMainBinding.executePendingBindings();
        loginViewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                Toast.makeText(MainActivity.this, "email : " + user.getEmail() + " password " + user.getPassword(), Toast.LENGTH_SHORT).show();
            }
        });

        loginViewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
