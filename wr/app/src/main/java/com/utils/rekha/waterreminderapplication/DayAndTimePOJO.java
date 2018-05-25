package com.utils.rekha.waterreminderapplication;

/**
 * Created by Gururaj on 5/24/2018.
 */
public class DayAndTimePOJO {
    public DayAndTimePOJO(int id, String time, String day, Boolean isEnabled) {
        this.id = id;
        this.time = time;
        this.day = day;
        this.isEnabled = isEnabled;
    }

    int id;

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

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    String time;
    String day;
    Boolean isEnabled;

}
