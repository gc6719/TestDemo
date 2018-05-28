package com.utils.rekha.waterreminderapplication;

import java.util.ArrayList;

/**
 * Created by Gururaj on 5/24/2018.
 */
public class DayAndTimePOJO {
    public DayAndTimePOJO(int id, String time, ArrayList<String> day, Boolean isEnabled, int hour, int minute) {
        this.id = id;
        this.time = time;
        this.day = day;
        this.isEnabled = isEnabled;
        this.hour = hour;
        this.minute = minute;
    }

    int id;

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ArrayList<String> getDay() {
        return day;
    }

    public void setDay(ArrayList<String> day) {
        this.day = day;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    String time;
    ArrayList<String> day;
    Boolean isEnabled;
    int hour;
    int minute;

}
