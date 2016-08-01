package com.ionov.workWithStatistic;

import com.ionov.UTF8Control;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by user on 01.08.2016.
 */
public class DataTableDetailReportDay implements Statistic {
    private static int count = 1;

    private int number;
    private String activity;
    private String time;
    private String begin;

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    private String end;
    private Locale locale = new Locale("ru", "RU");
    private ResourceBundle res = ResourceBundle.getBundle("columnName", locale, new UTF8Control());

    public DataTableDetailReportDay() {
        this.number = count;
        count++;
    }

    public static void setCount(int count) {
        DataTableDetailReportDay.count = count;
    }

    @Override
    public Object getData(String name){
        if(name.equals(res.getString("detailTable1"))){
            return number;
        }
        if(name.equals(res.getString("detailTable2"))){
            return activity;
        }
        if(name.equals(res.getString("detailTable3"))){
            return time;
        }
        if(name.equals(res.getString("detailTable4"))){
            return begin;
        }else{
            return end;
        }
    }

}
