package com.raymond.myrecipe;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

public class preferenceSettings extends AppCompatActivity {


    Switch sw_music, sw_note;
    Spinner spinner;
    static int hour = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference_settings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (findViewById(R.id.swmu) != null) {
            sw_music = (Switch) findViewById(R.id.swmu);
        }
        if (findViewById(R.id.swnt) != null) {
            sw_note = (Switch) findViewById(R.id.swnt);
        }

        buildSpinner();
        sw_music.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences pref = getSharedPreferences("sharePref", MODE_PRIVATE);
                if ((buttonView.getId() == R.id.swmu) && buttonView.isChecked()) {
                    Toast.makeText(preferenceSettings.this, "開啟音樂", Toast.LENGTH_SHORT).show();
                    pref.edit().clear().putBoolean("activated", true).apply();
                } else if ((buttonView.getId() == R.id.swmu) && (!buttonView.isChecked())) {
                    Toast.makeText(preferenceSettings.this, "音樂關閉", Toast.LENGTH_SHORT).show();
                    pref.edit().clear().putBoolean("activated", false).apply();
                }
            }
        });

        sw_note.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences pref = getSharedPreferences("Notification", MODE_PRIVATE);
                hour=itemToNum(spinner.getSelectedItem());
                if ((buttonView.getId() == R.id.swnt) && buttonView.isChecked()) {
                    Toast.makeText(preferenceSettings.this, "開啟定時通知", Toast.LENGTH_SHORT).show();
                    pref.edit().clear().putBoolean("note", true)
                            .putInt("hour", hour)
                            .apply();
                } else if ((buttonView.getId() == R.id.swnt) && (!buttonView.isChecked())) {
                    Toast.makeText(preferenceSettings.this, "關閉定時通知", Toast.LENGTH_SHORT).show();
                    pref.edit().clear().putBoolean("note", false).apply();
                }
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

//        SharedPreferences pref = getSharedPreferences("checked", MODE_PRIVATE);
        SharedPreferences pref_mu = getSharedPreferences("sharePref", MODE_PRIVATE);
        SharedPreferences pref_no = getSharedPreferences("Notification", MODE_PRIVATE);
        if (pref_mu.getBoolean("activated", false))
            sw_music.setChecked(true);
        else
            sw_music.setChecked(false);
        if (pref_no.getBoolean("note", false))
            sw_note.setChecked(true);
        else
            sw_note.setChecked(false);
        spinner.setSelection(hour);
    }

    void buildSpinner() {
        if (findViewById(R.id.timeList) != null) {
            spinner = (Spinner) findViewById(R.id.timeList);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (view.getId() == R.id.timeList) {
                        hour = parent.getSelectedItemPosition();

                        Toast.makeText(preferenceSettings.this, "重行設定時間"+String.valueOf(hour), Toast.LENGTH_SHORT).show();
                        SharedPreferences pref = getSharedPreferences("Notification", MODE_PRIVATE);
                        pref.edit().clear().putBoolean("note", true)
                                .putInt("hour", itemToNum(spinner.getSelectedItem()))
                                .apply();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    hour = 0;
                    Toast.makeText(preferenceSettings.this, "開啟定時通知", Toast.LENGTH_SHORT).show();
                    SharedPreferences pref = getSharedPreferences("Notification", MODE_PRIVATE);
                    pref.edit().clear().putBoolean("note", true)
                            .putInt("hour", hour)
                            .apply();
                }
            });
        }

    }


    int itemToNum(Object item) {
        String s = item.toString();
        int n = 0;
        if (s.equals("凌晨"))
            n = 0;
        else if (s.equals("上午1點"))
            n = 1;
        else if (s.equals("上午2點"))
            n = 2;
        else if (s.equals("上午3點"))
            n = 3;
        else if (s.equals("上午4點"))
            n = 4;
        else if (s.equals("上午5點"))
            n = 5;
        else if (s.equals("上午6點"))
            n = 6;
        else if (s.equals("上午7點"))
            n = 7;
        else if (s.equals("上午8點"))
            n = 8;
        else if (s.equals("上午9點"))
            n = 9;
        else if (s.equals("上午10點"))
            n = 10;
        else if (s.equals("上午11點"))
            n = 11;
        else if (s.equals("中午12點"))
            n = 12;
        else if (s.equals("下午13點"))
            n = 13;
        else if (s.equals("下午14點"))
            n = 14;
        else if (s.equals("下午15點"))
            n = 15;
        else if (s.equals("下午16點"))
            n = 16;
        else if (s.equals("下午17點"))
            n = 17;
        else if (s.equals("下午18點"))
            n = 18;
        else if (s.equals("下午19點"))
            n = 19;
        else if (s.equals("下午20點"))
            n = 20;
        else if (s.equals("下午21點"))
            n = 21;
        else if (s.equals("下午22點"))
            n = 22;
        else if (s.equals("下午23點"))
            n = 23;
        return n;

    }
}
