package com.utils.rekha.waterreminderapplication;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

/**
 * Created by Gururaj on 5/24/2018.
 */
public class DayTimeViewHolder extends RecyclerView.ViewHolder {

    TextView time, day;
    Switch time_switch;
    public DayTimeViewHolder(View itemView) {
        super(itemView);

        time = itemView.findViewById(R.id.time);
        day = itemView.findViewById(R.id.day);
        time_switch = itemView.findViewById(R.id.time_switch);
    }
}
