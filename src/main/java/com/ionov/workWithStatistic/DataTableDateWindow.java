package com.ionov.workWithStatistic;

import com.ionov.UTF8Control;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by user on 31.07.2016.
 */
public class DataTableDateWindow implements Statistic {
    private static int count = 1;

    private int number;
    private String activity;
    private String commonTime;
    private String ratioCommonTimeWatch;
    private String ratioCommonTime;
    private Locale locale = new Locale("ru", "RU");
    private ResourceBundle res = ResourceBundle.getBundle("columnName", locale, new UTF8Control());

    public DataTableDateWindow() {
        this.number = count;
        count++;
    }

    public static void setCount(int count) {
        DataTableDateWindow.count = count;
    }

    @Override
    public Object getData(String name){
        if(name.equals(res.getString("dateTableCol1"))){
            return number;
        }
        if(name.equals(res.getString("dateTableCol2"))){
            return activity;
        }
        if(name.equals(res.getString("dateTableCol3"))){
            return commonTime;
        }
        if(name.equals(res.getString("dateTableCol4"))){
            return ratioCommonTimeWatch;
        }else{
            return ratioCommonTime;
        }
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public void setCommonTime(String commonTime) {
        this.commonTime = commonTime;
    }

    public void setRatioCommonTime(String ratioCommonTime) {
        this.ratioCommonTime = ratioCommonTime;
    }

    public void setRatioCommonTimeWatch(String ratioCommonTimeWatch) {
        this.ratioCommonTimeWatch = ratioCommonTimeWatch;
    }
}
