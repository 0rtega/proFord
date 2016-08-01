package com.ionov.workWithStatistic;

import com.ionov.UTF8Control;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by user on 01.08.2016.
 */
public class DataTableSelect implements Statistic {

    private static int count = 1;

    private int number;
    private String activity;
    private String commonTime;
    private String date;
    private Locale locale = new Locale("ru", "RU");
    private ResourceBundle res = ResourceBundle.getBundle("columnName", locale, new UTF8Control());

    public DataTableSelect() {
        this.number = count;
        count++;
    }

    public static void setCount(int count) {
        DataTableSelect.count = count;
    }

    @Override
    public Object getData(String name) {
        if (name.equals(res.getString("selectTableCol1"))) {
            return number;
        }
        if (name.equals(res.getString("selectTableCol2"))) {
            return activity;
        }
        if (name.equals(res.getString("selectTableCol3"))) {
            return commonTime;
        } else {
            return date;
        }
    }

    public String getCommonTime() {
        return commonTime;
    }

    public String getDate() {
        return date;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public void setCommonTime(String commonTime) {
        this.commonTime = commonTime;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
