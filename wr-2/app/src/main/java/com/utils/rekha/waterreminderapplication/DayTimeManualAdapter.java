package com.utils.rekha.waterreminderapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import static android.media.CamcorderProfile.get;

/**
 * Created by Gururaj on 5/24/2018.
 */
public class DayTimeManualAdapter extends RecyclerView.Adapter<DayTimeViewHolder> {

    private List<DayAndTimePOJO> dayTimeList;
    private Context dayTimeContext;
    private  ManualTimeInterface mListener;


    public DayTimeManualAdapter(List<DayAndTimePOJO> dayTimeList, Context dayTimeContext,ManualTimeInterface mListener) {
        this.dayTimeList = dayTimeList;
        this.dayTimeContext = dayTimeContext;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public DayTimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.dat_and_time_view, null);
        return new DayTimeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DayTimeViewHolder holder, final int position) {
        String daySelectedByUser = "";
        DayAndTimePOJO dayAndTime = dayTimeList.get(position);
        holder.time.setText(dayAndTime.getTime());
        ArrayList<String> selectedDay = dayAndTime.getDay();
        for(int i=0; i<selectedDay.size(); i++)
        {
            if(i!=0)
                daySelectedByUser = daySelectedByUser + ", " +selectedDay.get(i);
            else
                daySelectedByUser = selectedDay.get(i);
        }
        holder.day.setText(daySelectedByUser);
        holder.time_switch.setChecked(dayAndTime.getEnabled());

        holder.time_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onManualTimeSetListener(position,holder.time_switch.isChecked());
            }
        });

    }


    @Override
    public int getItemCount() {
        return dayTimeList != null ? dayTimeList.size() : 0;
    }

    public  interface ManualTimeInterface
    {
        void onManualTimeSetListener(int position, Boolean isChecked);
    }
}
