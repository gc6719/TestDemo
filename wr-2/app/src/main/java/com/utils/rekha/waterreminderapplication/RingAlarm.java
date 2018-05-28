/*
package com.utils.rekha.waterreminderapplication;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.utils.rekha.waterreminderapplication.Util.UseLib;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RingAlarm extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    UseLib lib;
    SimpleDateFormat sdf;
    AlarmManager alarmManager;
    MediaPlayer mp;

    private final String CHANNEL_ID = "water_notification";
    private  final  int NOTIFICATION_ID = 1234;

    String currentTime_str, spWakeUpTime_str, spSleeptime_str;
    long currentTime_long,spWakeUpTime_long,spSleeptime_long;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getBundleExtra("action");
        String actionFromIntent = "startNotification" ;
        alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        if(bundle != null){
          actionFromIntent = bundle.getString("action","startNotification") ;
        }

        if (actionFromIntent != null && actionFromIntent.equals("startNotification")) {
            //initView();
            //getCurrentTime();

            spWakeUpTime_str = sharedPreferences.getString(UseLib.spWakeUpTime, null);
            spSleeptime_str = sharedPreferences.getString(UseLib.spSleepTime, null);

            try {
                spWakeUpTime_long = sdf.parse(spWakeUpTime_str).getTime();
                spSleeptime_long = sdf.parse(spSleeptime_str).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }

            alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            mp = MediaPlayer.create(getBaseContext(), R.raw.ttt);


            stopAlarm.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    mp.stop();
                    finish();
                    return false;
                }
            });
            playSound(this, getAlarmUri());


            cancelAlarm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = getIntent();
                    PendingIntent pendingIntent = PendingIntent.getActivity(RingAlarm.this, 12, intent, PendingIntent.FLAG_CANCEL_CURRENT);

                    // pendingIntent.cancel();
                    alarmManager.cancel(pendingIntent);
                    mp.stop();
                    finish();
                }
            });

        */
/*  //  if(currentTime_long>=spWakeUpTime_long && currentTime_long<=spSleeptime_long)
            {

            //    mp = MediaPlayer.create(getBaseContext(), R.raw.nestama);
                //               // playSound(this, getAlarmUri());
                showNotification();
                finish();
            }
        } else if (actionFromIntent != null && actionFromIntent.equals("cancelAlarm")) {
            Intent intent = getIntent();
            PendingIntent pendingIntent = PendingIntent.getActivity(RingAlarm.this, 12, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            // pendingIntent.cancel();
            alarmManager.cancel(pendingIntent);
            finish();
        }*//*
*/
/*else {
            finish();
        }
*//*


    }

    private  void getCurrentTime()
    {
        Calendar currentTimeCal = Calendar.getInstance();
        currentTime_str = sdf.format(currentTimeCal.getTime());
        try {
            currentTime_long = sdf.parse(currentTime_str).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private  void  initView()
    {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        lib = new UseLib();
        sdf = new SimpleDateFormat("hh:mm a");
    }

    private  void playSound(final Context context, Uri alert)
    {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mp.start();
                } catch (Throwable t) {
                    Log.d("Animation", "Thread Exception" + t);
                }
            }
        });
        thread.start();
    }
    @Override
    protected  void  onDestroy()
    {
        super.onDestroy();
        if(mp!=null && mp.isPlaying()) {
            mp.stop();
        }
    }

    private Uri getAlarmUri()
    {

        Uri alert= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if(alert==null)
        {
            alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            if (alert==null)
            {
                alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
            }
        }
        return alert;
    }
    public void showNotification( ) {

        createNotificationChannel();
        Intent intent = new Intent(this, RingAlarm.class);
        Bundle bundle = new Bundle();
        bundle.putString("action","cancelAlarm");
        intent.putExtra("action",bundle);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 12, intent,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_apps_notification);
        builder.setContentTitle("Water Reminder");
        builder.setContentText("Ddrink water now");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.addAction(R.drawable.ic_apps_notification,"Stop Alarm", pendingIntent);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        builder.setSound(getAlarmUri());
        builder.setVibrate(new long[]{1000, 1000});
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
*/
