/*
 * Copyright 2017 Vinícius Petrocione Jardim
 */

package com.vpjardim.contactsfix;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author Vinícius Jardim
 * 2017/10/24
 */
public class CodesActivity extends AppCompatActivity {

    public static final String TAG = "CodesActivity";

    private Toolbar toolbar;
    private EditText countryCodeET;
    private EditText areaCodeET;
    private FloatingActionButton startButton;

    @Override
    protected void onCreate(final Bundle saved) {

        super.onCreate(saved);
        Log.i(TAG, "onCreate ========>");

        setContentView(R.layout.activity_codes);

        toolbar       = (Toolbar) findViewById(R.id.toolbar);
        countryCodeET = (EditText) findViewById(R.id.etCCode);
        areaCodeET = (EditText) findViewById(R.id.etACode);
        startButton   = (FloatingActionButton) findViewById(R.id.btStart);

        setSupportActionBar(toolbar);

        // Todo fix button multi clicks problems

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                startButtonClick();
            }
        });
    }

    public void startButtonClick() {
        Formatter.DCC = countryCodeET.getText().toString();
        Formatter.DAC = areaCodeET.getText().toString();

        // Checking if default country code is ok
        Tables.CRow cRow = Tables.countryCodes.get(Formatter.DCC);
        if(cRow == null) {
            Toast.makeText(this, R.string.tt_err_country_code, Toast.LENGTH_SHORT).show();
            return;
        }

        // Checking if default area code is ok
        Tables.ARow aRow = Tables.areaCodes.get(Formatter.DCC + "/" + Formatter.DAC);
        if(aRow == null) {
            Toast.makeText(this, R.string.tt_err_area_code, Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("DCC", Formatter.DCC);
        intent.putExtra("DAC", Formatter.DAC);
        startActivity(intent);
    }
}