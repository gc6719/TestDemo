package com.utils.rekha.waterreminderapplication;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ManualTimerActivity extends AppCompatActivity {
    FloatingActionButton floatingActionButton;
    RecyclerView recycler_view;
    private List<DayAndTimePOJO> dayTimeList = new ArrayList<>();
    private DayTimeManualAdapter dayTimeManualAdapter;

    public  static  final int REQUEST_DAY_AND_TIME = 963;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_timer);


        recycler_view = findViewById(R.id.recycler_view);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));

        dayTimeManualAdapter = new DayTimeManualAdapter(dayTimeList,this);
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
                String day = data.getExtras().getString("SELECTEDDAY");
                String str = data.getExtras().getString("SELECTEDTIME");
                DayAndTimePOJO dayAndTimePOJO = new DayAndTimePOJO(1,str,day,true);
                dayTimeList.add(dayAndTimePOJO);
                dayTimeManualAdapter.notifyDataSetChanged();

                Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();
            }
        }
    }
}
