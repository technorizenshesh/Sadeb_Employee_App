package com.codeinger.sadeb_employee_app.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;


import com.codeinger.sadeb_employee_app.R;
import com.codeinger.sadeb_employee_app.databinding.ActivityChooseLanguageBinding;
import com.codeinger.sadeb_employee_app.utils.SharedPrefsManager;

import java.util.Locale;

public class ChooseLanguageActivity extends AppCompatActivity {

    ActivityChooseLanguageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_choose_language);
        binding.txtSingIn.setOnClickListener(v -> {
            setLocale("en");
        });
        binding.txtSingup.setOnClickListener(v -> {
            setLocale("es");
        });
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
        Intent i = new Intent(ChooseLanguageActivity.this, LoginActivity.class);
        startActivity(i);
        finish();

    }
}