package com.utils.rekha.waterreminderapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.utils.rekha.waterreminderapplication.Util.UseLib;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RingAlarm1 extends AppCompatActivity {

    MediaPlayer mp=null;
    AlarmManager alarmManager;
    Button stopAlarm,cancelAlarm;
    SharedPreferences sharedPreferences;
    long currentTime, s2l_wakeUpTime, s2l_SleepTime, s2l_intervalTime;
    String currentTime_string,wakeUpTime_str, sleepTime_str, intervalTime_str;
    UseLib library;
    SimpleDateFormat sdf1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ring_alarm1);
        initView();

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        mp = MediaPlayer.create(getBaseContext(), R.raw.nestama);


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
                PendingIntent pendingIntent = PendingIntent.getActivity(RingAlarm1.this, 12, intent, PendingIntent.FLAG_CANCEL_CURRENT);

                // pendingIntent.cancel();
                alarmManager.cancel(pendingIntent);
                mp.stop();
                finish();
            }
        });
    }
    private  void initView()
    {
        stopAlarm = (Button)findViewById(R.id.stopo_alarm);
        cancelAlarm = (Button)findViewById(R.id.cancel_alarm);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        library = new UseLib();
        sdf1 = new SimpleDateFormat();
    }

    private  void getCurrentTime()
    {
        sdf1 = new SimpleDateFormat("hh:mm a");
        Calendar calendar = Calendar.getInstance();
        currentTime_string = sdf1.format(calendar.getTime());
        try {
            currentTime = sdf1.parse(currentTime_string).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
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

}
