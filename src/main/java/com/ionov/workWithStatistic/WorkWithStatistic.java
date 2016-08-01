package com.ionov.workWithStatistic;

import com.ionov.NeedMethods;
import com.ionov.UTF8Control;
import com.ionov.workWithDB.WorkWithDB;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by user on 25.07.2016.
 */
public class WorkWithStatistic {
    private WorkWithDB work = WorkWithDB.getWork();
    private static WorkWithStatistic statistic = new WorkWithStatistic();
    private long checkInTime;
    private int commonTimeActivities;
    private NeedMethods need = NeedMethods.getNeed();
    private Locale locale = new Locale("ru", "RU");
    private ResourceBundle res = ResourceBundle.getBundle("columnName", locale, new UTF8Control());

    private WorkWithStatistic(){}

    public static WorkWithStatistic getStatistic() {
        return statistic;
    }

    //просто время регистрации
    public String getBeginWatch(String name){
        checkInTime = work.getCheckInTime(name);
        Date date1 = new Date(checkInTime);
        return new SimpleDateFormat("yyyy-MM-dd").format(date1);
    }
    //общее время наблюдения за всеми действиями
    public String getTimeWatch(String name){
         commonTimeActivities = work.getCommonTimeActivities(name);
        return need.getTimeString(commonTimeActivities);
    }


    //отношение общего времени набл к просто общему времени
    public String getRatioBeginWatch(){
        double a = ((double)commonTimeActivities / (new Date(checkInTime).getTime())/1000)*100;
        return new DecimalFormat("#0.00000").format(a) + "%";

    }
    //список названий колонок в главном окне втатистики по всем действиям
    public List<String> getColumnNameMainWindow(){
        ArrayList<String> list = new ArrayList<>();
        list.add(res.getString("mainTableCol1"));
        list.add(res.getString("mainTableCol2"));
        list.add(res.getString("mainTableCol3"));
        list.add(res.getString("mainTableCol4"));
        list.add(res.getString("mainTableCol5"));
        return list;
    }
    //список названий колонок в окне за дату  по всем действиям за месяц
    public List<String> getColumnNameDateWindow(){
        ArrayList<String> list = new ArrayList<>();
        list.add(res.getString("dateTableCol1"));
        list.add(res.getString("dateTableCol2"));
        list.add(res.getString("dateTableCol3"));
        list.add(res.getString("dateTableCol4"));
        list.add(res.getString("dateTableCol5"));
        return list;
    }

    //данные в табл в главном окне статистики по всем действиям за все время
    public List<Statistic> getDataTableMainWindow(String name){
        ArrayList<String> activities = work.getActivityByName(name);
        ArrayList<Statistic> list2 = new ArrayList<>();
        for(String i : activities){
            DataTableMainWindow table = new DataTableMainWindow();
            table.setActivity(i);
            int commonTime = work.getCommonTimeActivity(name, i);
            table.setCommonTime(need.getTimeString(commonTime));
            double a = ((double)commonTime/ commonTimeActivities)*100;
            table.setRatioCommonTimeWatch( new DecimalFormat("#0.000").format(a) + "%");
            double b = ((double)commonTime/ checkInTime)*100;
            table.setRatioCommonTime(new DecimalFormat("#0.00000").format(b) + "%");
            list2.add(table);
        }
        DataTableMainWindow.setCount(1);
        return list2;
    }
    //данные в табл в окне за дату по всем действиям за месяц
    public List<Statistic> getDataTableDateMonth(String name, String date){
        ArrayList<Statistic> list2 = new ArrayList<>();
        if(date != null) {
            String dateMonth = date.substring(5, 7);
            String dateYear = date.substring(0, 4);
            ArrayList<String> activities = work.getListActivitiesMonth(name, dateYear, dateMonth);
            Date d = new Date();
            d.setMonth(Integer.parseInt(dateMonth));
            d.setYear(Integer.parseInt(dateYear));
            int longMonth = need.getLongMonth(d);
            int time = longMonth * 24 * 3600;
            int commonTimeActivityMonth = work.getCommonTimeActivityMonth(name, dateYear, dateMonth);

            for (String i : activities) {
                DataTableDateWindow table = new DataTableDateWindow();
                table.setActivity(i);
                int commonTime = work.getTimeActivityMonth(name, dateMonth, dateYear, i);
                table.setCommonTime(need.getTimeString(commonTime));
                double a = ((double) commonTime / commonTimeActivityMonth) * 100;
                table.setRatioCommonTimeWatch(new DecimalFormat("#0.000").format(a) + "%");
                double b = ((double) commonTime / time) * 100;
                table.setRatioCommonTime(new DecimalFormat("#0.00000").format(b) + "%");
                list2.add(table);
            }
            DataTableDateWindow.setCount(1);
        }
        return list2;
    }
    //список месяцев с начала регистрации
    public ArrayList<String> getListMonthsByName(String name){
        return work.getListMonths(name);
    }
    //список действий по миени
    public ArrayList<String> getListActivity(String name){
        return work.getActivityByName(name);
    }
    //общее время действия за месяц какой то активности
    public String getTimeActivityMonth(String name, String date, String activity){
        String dateMonth = date.substring(5, 7);
        String dateYear = date.substring(0, 4);
         int timeActivityMonth = work.getTimeActivityMonth(name, dateMonth,dateYear,activity);
        return need.getTimeString(timeActivityMonth);
    }
    //отношение времени набл к общему времени всех набл за месяц
    public String getRatioWatchTimeMonth(String name, String date, String activity){
        String dateMonth = date.substring(5, 7);
        String dateYear = date.substring(0, 4);
        int time = work.getCommonTimeActivityMonth(name, dateYear, dateMonth);
        int timeActivityMonth = work.getTimeActivityMonth(name, dateMonth,dateYear,activity);
        double a = ((double)timeActivityMonth / time)*100;
        return new DecimalFormat("#0.00000").format(a) + "%";
    }

    //отношение времени набл к общему времени месяца
    public String getRatioCommonTimeMonth(String date, String activity, String name){
        String dateMonth = date.substring(5, 7);
        String dateYear = date.substring(0, 4);
        Date d = new Date();
        d.setMonth(Integer.parseInt(dateMonth));
        d.setYear(Integer.parseInt(dateYear));
        int longMonth = need.getLongMonth(d);
        int time = longMonth * 24 * 3600;
        int timeActivityMonth =  work.getTimeActivityMonth(name, dateMonth,dateYear,activity);
        double a = ((double)timeActivityMonth / time)*100;
        return new DecimalFormat("#0.00000").format(a) + "%";
    }
    //список колонок таблицы в окне выборки
    public ArrayList<String> getListColumnNameSelect(){
        ArrayList<String> list = new ArrayList<>();
        list.add(res.getString("selectTableCol1"));
        list.add(res.getString("selectTableCol2"));
        list.add(res.getString("selectTableCol3"));
        list.add(res.getString("selectTableCol4"));
        return list;
    }
    //список данных для таблицы выборки по одному действию за каждый день
    //когда было использоано это действие
    public ArrayList<Statistic> getDataTableSelectOneDay(String name, String activity){
        return work.getListDataSelectOneDay(name, activity);
    }

    //список данных для таблицы выборки по одному действию за каждый месяц
    //когда было использоано это действие
    public ArrayList<Statistic> getDataTableSelectOneMonth(String name, String activity){
        return work.getListDataSelectOneMonth(name, activity);
    }


    //имя колонок для табл в окне за день по дате. общий отчет
    public ArrayList<String> getColumnNameReportCommonDays(){
        ArrayList<String> list = new ArrayList<>();
        list.add(res.getString("dateCommonTableCol1"));
        list.add(res.getString("dateCommonTableCol2"));
        list.add(res.getString("dateCommonTableCol3"));
        list.add(res.getString("dateCommonTableCol4"));
        list.add(res.getString("dateCommonTableCol5"));
        return list;
    }
    //список данных для таблицы общего отчета за день
    public ArrayList<Statistic> getDataTableCommonReportDay(String name, String date, String day){
        ArrayList<Statistic> list2 = new ArrayList<>();
        if(date != null) {
            day = createDay(day);
            String dateMonth = date.substring(5, 7);
            String dateYear = date.substring(0, 4);
            ArrayList<String> activities = work.getListActivitiesDay(name, dateYear, dateMonth, day);
            int time = 24 * 3600;
            int commonTimeActivityDay = work.getCommonTimeActivityDay(name, dateYear, dateMonth, day);
            for (String i : activities) {
                DataTableCommonReportForDay table = new DataTableCommonReportForDay();
                table.setActivity(i);
                int commonTime = work.getTimeActivityDay(name, day, dateMonth, dateYear, i);
                table.setCommonTime(need.getTimeString(commonTime));
                double a = ((double) commonTime / commonTimeActivityDay) * 100;
                table.setRatioCommonTimeWatch(new DecimalFormat("#0.000").format(a) + "%");
                double b = ((double) commonTime / time) * 100;
                table.setRatioCommonTime(new DecimalFormat("#0.00000").format(b) + "%");
                list2.add(table);
            }
            DataTableCommonReportForDay.setCount(1);
        }
        return list2;
    }

    private String createDay(String day){
        if(day.length() < 2){
            return "0"+day;
        }else return day;
    }
    //список колонок для подробного точета за день
    public ArrayList<String> getColumnNameDetailReportDays(){
        ArrayList<String> list = new ArrayList<>();
        list.add(res.getString("detailTable1"));
        list.add(res.getString("detailTable2"));
        list.add(res.getString("detailTable3"));
        list.add(res.getString("detailTable4"));
        list.add(res.getString("detailTable5"));
        return list;
    }
    //для отчета за день подробного!
    public ArrayList<Statistic> getDataTableDetailReportDay(String name, String date, String day){
        String dateMonth = date.substring(5, 7);
        String dateYear = date.substring(0, 4);
        DataTableDetailReportDay.setCount(1);
        day = createDay(day);
        return work.getListStatisticForDay(name, dateMonth, dateYear, day);
    }

    //для отчета за день по активностям
    public ArrayList<Statistic> getDataTableDetailReportDayForOneActivity(String name, String date, String day,
                                                                          String activity){
        String dateMonth = date.substring(5, 7);
        String dateYear = date.substring(0, 4);
        DataTableDetailReportDay.setCount(1);
        day = createDay(day);
        return work.getListStatisticForDayForOneActivity(name, dateMonth, dateYear, day, activity);
    }
    //общее время активности за какой то день
    public String getTimeActivityDay(String name, String date, String day, String activity){
        String dateMonth = date.substring(5, 7);
        String dateYear = date.substring(0, 4);
        day = createDay(day);
        int timeActivityDay = work.getTimeActivityDay(name, day, dateMonth,dateYear, activity);
        return need.getTimeString(timeActivityDay);
    }
    //отношение времени набл за одним дейст за один день к общему времени набл за всеми дейст за
    //1 день
    public String getRatioDayAndCommonWatchTime(String name, String date, String day, String activity){
        String dateMonth = date.substring(5, 7);
        String dateYear = date.substring(0, 4);
        day = createDay(day);
        int time = work.getCommonTimeActivityDay(name, dateYear, dateMonth, day );
        int timeActivityDay = work.getTimeActivityDay(name, day, dateMonth,dateYear, activity);
        double a = ((double)timeActivityDay / time)*100;
        return new DecimalFormat("#0.00000").format(a) + "%";
    }
    //отношение общего времени набл действия за день к реально прошедшему времени за день
    public String getRatioDayCommonTimeDay(String name, String date, String day, String activity){
        String dateMonth = date.substring(5, 7);
        String dateYear = date.substring(0, 4);
        day = createDay(day);
        int time =  24 * 3600;
        int timeActivityDay = work.getTimeActivityDay(name, day, dateMonth,dateYear, activity);
        double a = ((double)timeActivityDay / time)*100;
        return new DecimalFormat("#0.00000").format(a) + "%";
    }
}
