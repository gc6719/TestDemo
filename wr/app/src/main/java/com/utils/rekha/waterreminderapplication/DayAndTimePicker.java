package com.utils.rekha.waterreminderapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.List;

import ca.antonious.materialdaypicker.MaterialDayPicker;

public class DayAndTimePicker extends AppCompatActivity {
    TextView show_time;
    TimePicker mtimePicker;
    MaterialDayPicker day_picker;
    Button set_manual_time;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_and_time_picker);
        mtimePicker = findViewById(R.id.mtimePicker);
        day_picker = findViewById(R.id.day_picker);
        set_manual_time = findViewById(R.id.add_btn);
        show_time = findViewById(R.id.show_time);

        set_manual_time.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                String mTime  = mtimePicker.getCurrentHour()+ ":"
                        + mtimePicker.getCurrentMinute();


                List<MaterialDayPicker.Weekday> days = day_picker.getSelectedDays();
                List<String> temp = new ArrayList<>();
                for(int i =0; i<days.size(); i++)
                {
                   MaterialDayPicker.Weekday weekday =  days.get(i);
                   temp.add(weekday.toString().substring(0,3));
                }

               show_time.setText(mtimePicker.getCurrentHour()+ ":"
                       + mtimePicker.getCurrentMinute()+
                       day_picker.getSelectedDays().toString());

                Intent intent = getIntent();
                intent.putExtra("SELECTEDTIME", mTime);
                intent.putExtra("SELECTEDDAY",temp.toString() );
                setResult(RESULT_OK, intent);
                finish();


            }
        });
    }
}
