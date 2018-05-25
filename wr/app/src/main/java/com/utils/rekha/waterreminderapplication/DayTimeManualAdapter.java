package com.utils.rekha.waterreminderapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import static android.media.CamcorderProfile.get;

/**
 * Created by Gururaj on 5/24/2018.
 */
public class DayTimeManualAdapter extends RecyclerView.Adapter<DayTimeViewHolder> {

    private List<DayAndTimePOJO> dayTimeList;
    private Context dayTimeContext;

    public DayTimeManualAdapter(List<DayAndTimePOJO> dayTimeList, Context dayTimeContext) {
        this.dayTimeList = dayTimeList;
        this.dayTimeContext = dayTimeContext;
    }

    @NonNull
    @Override
    public DayTimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.dat_and_time_view, null);
        return new DayTimeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DayTimeViewHolder holder, int position) {
        DayAndTimePOJO dayAndTime = dayTimeList.get(position);
        holder.time.setText(dayAndTime.getTime());
        holder.day.setText(dayAndTime.getDay());

    }

    @Override
    public int getItemCount() {
        return dayTimeList != null ? dayTimeList.size() : 0;
    }
}
