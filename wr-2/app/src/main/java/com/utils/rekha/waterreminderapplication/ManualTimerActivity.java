package com.utils.rekha.waterreminderapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class ManualTimerActivity extends AppCompatActivity implements DayTimeManualAdapter.ManualTimeInterface{
    FloatingActionButton floatingActionButton;
    RecyclerView recycler_view;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    Button test;
    private List<DayAndTimePOJO> dayTimeList = new ArrayList<>();
    private DayTimeManualAdapter dayTimeManualAdapter;

    public  static  final int REQUEST_DAY_AND_TIME = 963;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_timer);


        recycler_view = findViewById(R.id.recycler_view);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));

        dayTimeManualAdapter = new DayTimeManualAdapter(dayTimeList,this,this);
        recycler_view.setAdapter(dayTimeManualAdapter);

        floatingActionButton = findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManualTimerActivity.this, DayAndTimePicker.class);
                startActivityForResult(intent,REQUEST_DAY_AND_TIME);
            }
        });
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_DAY_AND_TIME)
        {
            if(resultCode == RESULT_OK)
            {
                ArrayList<String> day = data.getStringArrayListExtra("SELECTEDDAY");
                String time = data.getExtras().getString("SELECTEDTIME");
                Integer hour = data.getExtras().getInt("SELECTED_HOUR",0);
                Integer min = data.getExtras().getInt("SELECTED_MIN",0);
                DayAndTimePOJO dayAndTimePOJO = new DayAndTimePOJO(generateAlarmId(),time,day,false,hour,min);
                dayTimeList.add(dayAndTimePOJO);
                dayTimeManualAdapter.notifyDataSetChanged();

                Toast.makeText(getApplicationContext(),time,Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setAlarmForDay(int day, int hour, int min, int id)
    {
        Calendar calendar = Calendar.getInstance();

        int presentDay = calendar.get(Calendar.DAY_OF_WEEK);
        if(day<presentDay)
        {
            //(7-presentDay)+selectedDay
            calendar.add(Calendar.DAY_OF_WEEK,(7-presentDay)+day);
        }
        else //if(day>presentDay)
        {
            //selectedDay-presentDay
            calendar.set(Calendar.DAY_OF_WEEK, day);
        }

        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE,min);

        long triggertime = calendar.getTime().getTime();
        long repeatTime = getRepeatTimrForGivenDate(calendar,7);
        Intent intent = new Intent(ManualTimerActivity.this, RingAlarm1.class);
        pendingIntent = PendingIntent.getActivity(this,id+day,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,triggertime,repeatTime,pendingIntent);
    }

    private  long getRepeatTimrForGivenDate(Calendar calendar,int numOfDays)
    {
        Calendar futureCal = Calendar.getInstance();
        futureCal.setTime(calendar.getTime());

        futureCal.add(Calendar.DAY_OF_YEAR,numOfDays);
        long intervalTime = futureCal.getTime().getTime()-calendar.getTime().getTime();
        return  intervalTime;
    }

    @Override
    public void onManualTimeSetListener(int position,Boolean isChecked) {
        DayAndTimePOJO dayAndTimePOJO = dayTimeList.get(position);
        dayAndTimePOJO.setEnabled(isChecked);
        dayTimeManualAdapter.notifyDataSetChanged();
        if(isChecked)
        {

            ArrayList<String> days = dayAndTimePOJO.getDay();
            for(String day : days)
            {
                setAlarmForDay(getDayInInt(day),dayAndTimePOJO.getHour(),
                        dayAndTimePOJO.getMinute(),dayAndTimePOJO.getId());
            }
        }
        else
        {
            alarmManager.cancel(pendingIntent);
        }


    }

    private  int getDayInInt(String dayName)
    {
        if(dayName.equalsIgnoreCase("Sun"))
            return Calendar.SUNDAY;
        else if(dayName.equalsIgnoreCase("Mon"))
            return Calendar.MONDAY;
        else if(dayName.equalsIgnoreCase("Tue"))
            return Calendar.TUESDAY;
        else if(dayName.equalsIgnoreCase("Wed"))
            return Calendar.WEDNESDAY;
        else if(dayName.equalsIgnoreCase("Thu"))
            return Calendar.THURSDAY;
        else if(dayName.equalsIgnoreCase("Fri"))
            return Calendar.FRIDAY;
        else if (dayName.equalsIgnoreCase("Sat"))
            return Calendar.SATURDAY;
        else
            return 0;
   }

    private  int generateAlarmId()
    {
        Random random = new Random();

        return  dayTimeList.size()+1+ random.nextInt(99);
    }
}
