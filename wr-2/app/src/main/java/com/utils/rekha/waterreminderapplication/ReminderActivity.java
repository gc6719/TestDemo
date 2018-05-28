package com.utils.rekha.waterreminderapplication;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.utils.rekha.waterreminderapplication.Util.UseLib;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ReminderActivity extends AppCompatActivity {

    ImageView wakeup_img, sleep_img, interval_img;
    TextView wakeup_txt, wakeup_time, sleep_txt, sleep_time, interval_txt,interval_time;
    LinearLayout wakeup_layout, sleep_layout, interval_layout;
    RelativeLayout repeatLayout;

    private final String CHANNEL_ID = "water_notification";
    private  final  int NOTIFICATION_ID = 1234;

    Button btnstartAlarm;
    String getWakeupTime, getSleepTime,getIntervalTime;
    long getIntervalTime_long;

    SimpleDateFormat sdf1;
    SimpleDateFormat sdf2;

    Calendar globalCal;
    SharedPreferences sharedPreferences;
    UseLib lib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        initView();
        wakeupTimePicker();
        sleepTimePicker();
        intervalTimePicker();
    }

    TimePickerDialog.OnTimeSetListener time1 = new TimePickerDialog.OnTimeSetListener() {
        Calendar calendar = Calendar.getInstance();
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
            calendar.set(Calendar.MINUTE, minute);
            updateWakeupTimeEdit(sdf1.format(calendar.getTime()));
        }
    };
    TimePickerDialog.OnTimeSetListener time2 = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            Calendar sleepCal = Calendar.getInstance();
            sleepCal.set(Calendar.HOUR_OF_DAY, hourOfDay);
            sleepCal.set(Calendar.MINUTE, minute);
            updateSleepTimeEdit(sdf1.format(sleepCal.getTime()));
        }
    };

    TimePickerDialog.OnTimeSetListener time3 = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            Calendar intervalCal = Calendar.getInstance();
            intervalCal.set(Calendar.HOUR_OF_DAY, hourOfDay);
            intervalCal.set(Calendar.MINUTE, minute);
            updateIntervalTimeEdit(sdf2.format(intervalCal.getTime()));
        }
    };

    private void updateIntervalTimeEdit(String intervalTimeEdit) {
        interval_time.setText(intervalTimeEdit);
        getIntervalTime = interval_time.getText().toString();
        lib.saveString(UseLib.spIntervalTime, getIntervalTime, this);

    }

    private void updateSleepTimeEdit(String sleepTimeEdit) {
        sleep_time.setText(sleepTimeEdit);
        getSleepTime = sleep_time.getText().toString();
        lib.saveString(UseLib.spSleepTime, getSleepTime, this);
    }

    private void updateWakeupTimeEdit(String wakeupEdit) {
        wakeup_time.setText(wakeupEdit);
        getWakeupTime = wakeup_time.getText().toString();
        lib.saveString(UseLib.spWakeUpTime, getWakeupTime, this);
       }

       private  void intervalTimePicker()
       {
           interval_layout.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   new TimePickerDialog(ReminderActivity.this, time3,
                           globalCal.get(Calendar.HOUR_OF_DAY),
                           globalCal.get(Calendar.MINUTE), true).show();
               }
           });
       }

    private  void wakeupTimePicker()
    {
        wakeup_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(ReminderActivity.this,time1,
                        globalCal.get(Calendar.HOUR_OF_DAY),
                globalCal.get(Calendar.MINUTE),false).show();
            }
        });
    }

    private  void sleepTimePicker()
    {
        sleep_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(ReminderActivity.this, time2,
                        globalCal.get(Calendar.HOUR_OF_DAY),
                        globalCal.get(Calendar.MINUTE),false).show();
            }
        });
    }

    private  void setDefaults()
    {
        wakeup_time.setText(sdf1.format(globalCal.getTime()));
        sleep_time.setText(sdf1.format(globalCal.getTime()));
        interval_time.setText(sdf2.format(globalCal.getTime()));
    }

    private  void initView()
    {
        wakeup_layout = (LinearLayout) findViewById(R.id.wakeup_layout);
        sleep_layout = (LinearLayout) findViewById(R.id.sleep_layout);
        interval_layout = (LinearLayout) findViewById(R.id.interval_layout);

        wakeup_img = (ImageView) findViewById(R.id.wakeup_img);
        sleep_img = (ImageView) findViewById(R.id.sleep_img);
        interval_img = (ImageView) findViewById(R.id.interval_img);

        wakeup_txt = (TextView) findViewById(R.id.wakeup_txt);
        wakeup_time = (TextView) findViewById(R.id.wakeup_time);
        sleep_txt = (TextView) findViewById(R.id.sleep_txt);
        sleep_time = (TextView) findViewById(R.id.sleep_time);
        interval_txt = (TextView) findViewById(R.id.interval_txt);
        interval_time = (TextView) findViewById(R.id.interval_time);

        sdf1 = new SimpleDateFormat("hh:mm a");
        sdf2 = new SimpleDateFormat("HH:mm");

        btnstartAlarm = (Button) findViewById(R.id.start_alarm);
        btnstartAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAlarm(sharedPreferences.getString(UseLib.spIntervalTime,"00:01"));
            }
        });

        globalCal = Calendar.getInstance();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        lib = new UseLib();
        setDefaults();

    }

    private void startAlarm(String intervaltime) {
        long temp = 0 ;
        try {
            temp =  sdf2.parse(intervaltime) .getTime() ;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(ReminderActivity.this, RingAlarm1.class);
        Bundle bundle = new Bundle();
        bundle.putString("action","startNotification");
        intent.putExtra("action",bundle);
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getActivity(ReminderActivity.this,
                0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                temp,temp,pendingIntent);
        Toast.makeText(getApplicationContext(),"Alarm set", Toast.LENGTH_SHORT).show();
    }

    public void showNotification(View view) {

        createNotificationChannel();

        Intent intent = new Intent(this, RingAlarm1.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 12, intent,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_apps_notification);
        builder.setContentTitle("Water Reminder");
        builder.setContentText("Ddrink water now");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.addAction(R.drawable.ic_apps_notification,"Stop", pendingIntent);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(NOTIFICATION_ID, builder.build());
    }

    private  void createNotificationChannel()
    {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            CharSequence name = "water Notification";
            String description = "Include all the notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            manager.createNotificationChannel(channel);
        }

    }
}
