package com.codeinger.sadeb_employee_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.codeinger.sadeb_employee_app.R;
import com.codeinger.sadeb_employee_app.network.model.ResponseAuthenticationResponse;
import com.codeinger.sadeb_employee_app.utils.SharedPrefsManager;

import java.util.Locale;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if(SharedPrefsManager.getInstance().getString("language").equals("")){
            SharedPrefsManager.getInstance().setString("language","en");
        }
        setLocale(SharedPrefsManager.getInstance().getString("language"));

        finds();
    }

    public void setLocale(String lang) {
        // Create a new Locale object
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        // Create a new configuration object
        Configuration config = new Configuration();
        // Set the locale of the new configuration
        config.locale = locale;
        // Update the configuration of the Accplication context
        getResources().updateConfiguration(
                config,
                getResources().getDisplayMetrics()
        );
        SharedPrefsManager.getInstance().setString("language",lang);

    }

    private void finds() {

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                ResponseAuthenticationResponse model = SharedPrefsManager.getInstance().getObject("employee", ResponseAuthenticationResponse.class);
                if (model!=null){
                    Log.i("sfsfdd", "run: "+12);
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                }else {
                    Log.i("sfsfdd", "run: "+12342);
                    Intent i = new Intent(SplashActivity.this, ChooseLanguageActivity.class);
                    startActivity(i);
                }
                finish();
            }
        }, 3000);
    }

}