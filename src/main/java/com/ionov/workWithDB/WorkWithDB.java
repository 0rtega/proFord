package com.ionov.workWithDB;


import com.ionov.NeedMethods;
import com.ionov.workWithStatistic.DataTableDetailReportDay;
import com.ionov.workWithStatistic.DataTableSelect;
import com.ionov.workWithStatistic.Statistic;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class WorkWithDB {

    private static WorkWithDB work = new WorkWithDB();
    private ConnectionDB connectionDB = ConnectionDB.getConnectionDB();

    private WorkWithDB() {
    }

    public static WorkWithDB getWork() {
        return work;
    }

    public boolean isExistDB() {
        boolean isExist = true;
        Statement stmt = null;
        Connection c = connectionDB.getConnection();
        try {
            stmt = c.createStatement();
            ResultSet r1 = stmt.executeQuery("SELECT * FROM USERS;");
            while (r1.next()) {
                r1.getInt("id");
                r1.getString("password");
                r1.getString("name");
                r1.getInt("createdate");
            }
            ResultSet r2 = stmt.executeQuery("SELECT * FROM STATISTIC;");
            while (r2.next()) {
                r2.getInt("id");
                r2.getString("activity");
                r2.getString("name");
                r2.getInt("time");
                r2.getString("year");
                r2.getString("month");
                r2.getString("day");
                r2.getString("timebegin");
                r2.getString("timeend");
            }

            ResultSet r3 = stmt.executeQuery("SELECT * FROM ACTIVITY;");
            while (r3.next()) {
                r3.getInt("id");
                r3.getString("activity");
                r3.getString("name");
            }
            stmt.close();
        } catch (SQLException e) {
            isExist = false;
            e.printStackTrace();
        }
        return isExist;
    }

    public void createDB() {
        Statement stmt = null;
        Connection c = connectionDB.getConnection();
        try {
            stmt = c.createStatement();

            String sql = "CREATE TABLE USERS " +
                    "(ID INT PRIMARY KEY     NOT NULL," +
                    " NAME           TEXT    NOT NULL," +
                    " PASSWORD        TEXT NOT NULL," +
                    "CREATEDATE INTEGER NOT NULL)";
            stmt.executeUpdate(sql);

            String sql1 = "INSERT INTO USERS (ID,NAME,PASSWORD, CREATEDATE)" +
                    "VALUES (1, 'Paul', '12', 99999999);";
            stmt.executeUpdate(sql1);

            String sql2 = "CREATE TABLE STATISTIC " +
                    "(ID INT PRIMARY KEY     NOT NULL," +
                    " ACTIVITY           TEXT    NOT NULL, " +
                    " NAME        TEXT     NOT NULL," +
                    "TIME INT NOT NULL," +
                    "YEAR TEXT NOT NULL," +
                    "MONTH TEXT NOT NULL," +
                    "DAY TEXT NOT NULL," +
                    "TIMEBEGIN TEXT NOT NULL," +
                    "TIMEEND TEXT  NOT NULL) ";
            stmt.executeUpdate(sql2);

            String sql7 = "INSERT INTO STATISTIC (ID, ACTIVITY, NAME, TIME," +
                    "YEAR, MONTH, DAY, TIMEBEGIN, TIMEEND)" +
                    "VALUES (1, 'курение', 'ivan', 1234, '2016', '6', '12', '12:12:12', '13:13:13');";
            stmt.executeUpdate(sql7);



//            String sql8 = "INSERT INTO STATISTIC (ID, ACTIVITY, NAME, TIME," +
//                    "YEAR, MONTH, DAY, TIMEBEGIN, TIMEEND)" +
//                    "VALUES (1, 'курение', 'Paul', 1234, '2016', '6', '12', '12:12:12', '13:13:13');";
//            stmt.executeUpdate(sql8);
//
//            String sql9 = "INSERT INTO STATISTIC (ID, ACTIVITY, NAME, TIME," +
//                    "YEAR, MONTH, DAY, TIMEBEGIN, TIMEEND)" +
//                    "VALUES (1, 'курение', 'Paul', 1234, '2016', '5', '13', '12:12:12', '13:13:13');";
//            stmt.executeUpdate(sql9);

            String sql4 = "CREATE TABLE ACTIVITY " +
                    "(ID INT PRIMARY KEY     NOT NULL," +
                    " ACTIVITY           TEXT    NOT NULL, " +
                    " NAME        TEXT     NOT NULL)";
            stmt.executeUpdate(sql4);

            String sql5 = "INSERT INTO ACTIVITY (ID, ACTIVITY, NAME)" +
                    "VALUES (1, 'курение', 'ivan');";
            stmt.executeUpdate(sql5);

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * возвращает true если имя есть в бд и false если нет.
     *
     * @param name
     * @return
     */
    public boolean isExistName(String name) {
        boolean isExist = true;
        PreparedStatement stmt = null;
        Connection c = connectionDB.getConnection();
        try {
            stmt = c.prepareStatement("SELECT * FROM USERS WHERE name = ?");
            stmt.setString(1, name);
            ResultSet resultSet = stmt.executeQuery();
            if (!resultSet.next()) {
                isExist = false;
            }

        } catch (SQLException e) {
            isExist = false;
            e.printStackTrace();
        }
        return isExist;
    }

    public int getCountActivity(String name) {
        List<String> list = getActivityByName(name);
        return list.size();
    }


    public boolean isExistUsers() {
        boolean isExist = true;
        PreparedStatement stmt = null;
        Connection c = connectionDB.getConnection();
        try {
            stmt = c.prepareStatement("SELECT * FROM USERS");
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                resultSet.getString("name");
            }
        } catch (SQLException e) {
            isExist = false;
            e.printStackTrace();
        }
        return isExist;
    }

    public String getPassword(String name) {
        String sd = null;
        try {
            PreparedStatement statement = connectionDB.getConnection().prepareStatement(
                    "SELECT * FROM USERS WHERE name = ?");
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                sd = rs.getString("password");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return sd;
    }


    public ArrayList<String> getListMonths(String name){
        ArrayList<String> list = new ArrayList<>();
        try {
            PreparedStatement statement = connectionDB.getConnection().prepareStatement(
                    "select distinct month, year from statistic where name = '"+ name+"'");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String a = rs.getString("month");
                if(a.length() < 2){
                    list.add(rs.getString("year") + "-0" + a);
                }else {
                    list.add(rs.getString("year") +"-" + a);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        Collections.sort(list);
        Collections.reverse(list);
        return list;
    }

    public ArrayList<String> getListActivitiesMonth(String name, String year, String month){
        ArrayList<String> list = new ArrayList<>();
        try {
            PreparedStatement statement = connectionDB.getConnection().prepareStatement(
                    "select distinct activity from statistic where name = '"+ name+"'" +
                            "and year = '"+year+"' and month = '"+month+"'");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
               list.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        Collections.sort(list);
        Collections.reverse(list);
        return list;
    }

    public ArrayList<String> getListActivitiesDay(String name, String year, String month, String day){
        ArrayList<String> list = new ArrayList<>();
        try {
            PreparedStatement statement = connectionDB.getConnection().prepareStatement(
                    "select distinct activity from statistic where name = '"+ name+"'" +
                            "and year = '"+year+"' and month = '"+month+"'" +
                            "and day = '"+day+"'");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        Collections.sort(list);
        Collections.reverse(list);
        return list;
    }

    public ArrayList<Integer> getListDaysInMonthWhenWereActivities(String name, String month, String year){
        ArrayList<Integer> list = new ArrayList<>();
        try {
            PreparedStatement statement = connectionDB.getConnection().prepareStatement(
                    "select distinct day from statistic where name = '"+ name+"' " +
                            " and month = '" + month + "' and year = '" + year+ "'" );
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public ArrayList<String> getListMonthsByActivityAndName(String name, String activity){
        ArrayList<String> list = new ArrayList<>();
        try {
            PreparedStatement statement = connectionDB.getConnection().prepareStatement(
                    "select distinct month, year from statistic where name = '"+ name+"'" +
                            "and activity = '"+ activity+"'");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String a = rs.getString("month");
                if(a.length() < 2){
                    list.add(rs.getString("year") + "-0" + a);
                }else {
                    list.add(rs.getString("year") +"-" + a);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        Collections.sort(list);
        Collections.reverse(list);
        return list;
    }

    public ArrayList<Integer> getListDaysInMonthWhenWereActivity(String name, String month, String year, String activity){
        ArrayList<Integer> list = new ArrayList<>();
        try {
            PreparedStatement statement = connectionDB.getConnection().prepareStatement(
                    "select distinct day from statistic where name = '"+ name+"' " +
                            " and month = '" + month + "' and year = '" + year+ "' and activity = '"+ activity+"'" );
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }


    public ArrayList<Statistic> getListStatisticForDay(String name, String month, String year, String day){
        ArrayList<Statistic> list = new ArrayList<>();
        try {
            PreparedStatement statement = connectionDB.getConnection().prepareStatement(
                    "select *  from statistic where name = '"+ name+"' " +
                            " and month = '" + month + "' and year = '" + year+ "' and day = '"+ day+"'" );
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                DataTableDetailReportDay report = new DataTableDetailReportDay();
                report.setActivity(rs.getString("activity"));
                report.setTime(NeedMethods.getNeed().getTimeString(rs.getInt("time")));
                report.setBegin(rs.getString("timebegin"));
                report.setEnd(rs.getString("timeend"));
                list.add(report);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public ArrayList<Statistic> getListStatisticForDayForOneActivity(String name, String month, String year, String day,String activity){
        ArrayList<Statistic> list = new ArrayList<>();
        try {
            PreparedStatement statement = connectionDB.getConnection().prepareStatement(
                    "select *  from statistic where name = '"+ name+"' " +
                            " and month = '" + month + "' and year = '" + year+ "' and day = '"+ day+"'" +
                            "and activity = '"+activity+"'" );
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                DataTableDetailReportDay report = new DataTableDetailReportDay();
                report.setActivity(rs.getString("activity"));
                report.setTime(NeedMethods.getNeed().getTimeString(rs.getInt("time")));
                report.setBegin(rs.getString("timebegin"));
                report.setEnd(rs.getString("timeend"));
                list.add(report);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public ArrayList<Statistic> getListDataSelectOneDay(String name, String activity){
        ArrayList<Statistic> list = new ArrayList<>();

        try {
            PreparedStatement statement = connectionDB.getConnection().prepareStatement(
                    "select distinct month, year, day from statistic where name = '"+ name+"'" +
                            "and activity = '"+ activity+"'");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                DataTableSelect tableSelect = new DataTableSelect();
                String year = rs.getString("year");
                String month = rs.getString("month");
                String day = rs.getString("day");
                tableSelect.setActivity(activity);
                tableSelect.setCommonTime(NeedMethods.getNeed()
                        .getTimeString(getCommonTimeActivityDay(name, year, month, day)));
                tableSelect.setDate(year + "-" + month + "-" + day);
                list.add(tableSelect);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        DataTableSelect.setCount(1);
        return list;
    }


    public ArrayList<Statistic> getListDataSelectOneMonth(String name, String activity){
        ArrayList<Statistic> list = new ArrayList<>();

        try {
            PreparedStatement statement = connectionDB.getConnection().prepareStatement(
                    "select distinct month, year from statistic where name = '"+ name+"'" +
                            "and activity = '"+ activity+"'");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                DataTableSelect tableSelect = new DataTableSelect();
                String year = rs.getString("year");
                String month = rs.getString("month");
                tableSelect.setActivity(activity);
                tableSelect.setCommonTime(NeedMethods.getNeed()
                        .getTimeString(getTimeActivityMonth(name,  month, year, activity)));
                tableSelect.setDate(year + "-" + month);
                list.add(tableSelect);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        DataTableSelect.setCount(1);
        return list;
    }



    public long getCheckInTime(String name){
        long sd = 0;
        try {
            PreparedStatement statement = connectionDB.getConnection().prepareStatement(
                    "SELECT * FROM USERS WHERE name = ?");
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                sd = rs.getLong("createdate");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return sd;
    }


    public int getCommonTimeActivities(String name){
        int a = 0;
        Statement stmt = null;
        try {
            stmt = connectionDB.getConnection().createStatement();
            ResultSet r1 = stmt.executeQuery("SELECT sum(time) FROM STATISTIC WHERE name = '"+name+"'");
           a = r1.getInt(1);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }

    public int getCommonTimeActivity(String name , String activity){
        int a = 0;
        Statement stmt = null;
        try {
            stmt = connectionDB.getConnection().createStatement();
            ResultSet r1 = stmt.executeQuery("SELECT sum(time) FROM STATISTIC WHERE name = '"+name+"'" +
                    "and activity = '"+activity+"'");
            a = r1.getInt(1);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }


    public int getTimeActivityMonth(String name, String month,String year,  String activity){
        int a = 0;
        Statement stmt = null;
        try {
            stmt = connectionDB.getConnection().createStatement();
            ResultSet r1 = stmt.executeQuery("SELECT sum(time) FROM STATISTIC WHERE name = '"+name+"'" +
                    "and activity = '"+activity+"' and month= '"+month+"' and year = '"+year +"'");
            a = r1.getInt(1);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }

    public int getTimeActivityDay(String name, String day, String month,String year,  String activity){
        int a = 0;
        Statement stmt = null;
        try {
            stmt = connectionDB.getConnection().createStatement();
            ResultSet r1 = stmt.executeQuery("SELECT sum(time) FROM STATISTIC WHERE name = '"+name+"'" +
                    "and activity = '"+activity+"' and month= '"+month+"' and year = '"+year +"'" +
                    "and day ='"+ day+"'");
            a = r1.getInt(1);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }

    public int getCommonTimeActivityMonth(String name, String year, String month){
        int a = 0;
        Statement stmt = null;
        try {
            stmt = connectionDB.getConnection().createStatement();
            ResultSet r1 = stmt.executeQuery("SELECT sum(time) FROM STATISTIC WHERE name = '"+name+"'" +
                    " and month= '"+month+"' and year = '"+year +"'");
            a = r1.getInt(1);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }

    public int getCommonTimeActivityDay(String name, String year, String month, String day){
        int a = 0;
        Statement stmt = null;
        try {
            stmt = connectionDB.getConnection().createStatement();
            ResultSet r1 = stmt.executeQuery("SELECT sum(time) FROM STATISTIC WHERE name = '"+name+"'" +
                    " and month= '"+month+"' and year = '"+year +"' and day = '"+day+"'");
            a = r1.getInt(1);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }

    private int getMaxIdActivity() {
        int a = 0;
        Statement stmt = null;
        try {
            stmt = connectionDB.getConnection().createStatement();
            ResultSet r1 = stmt.executeQuery("SELECT * FROM ACTIVITY ORDER BY ID DESC LIMIT 1");
            while (r1.next()) {
                a = r1.getInt("id");
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }

    private int getMaxIdUsers() {
        int a = 0;
        Statement stmt = null;
        try {
            stmt = connectionDB.getConnection().createStatement();
            ResultSet r1 = stmt.executeQuery("SELECT * FROM USERS ORDER BY ID DESC LIMIT 1");
            while (r1.next()) {
                a = r1.getInt("id");
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }

    private int getMaxIdStatistic() {
        int a = 0;
        Statement stmt = null;
        try {
            stmt = connectionDB.getConnection().createStatement();
            ResultSet r1 = stmt.executeQuery("SELECT * FROM STATISTIC ORDER BY ID DESC LIMIT 1");
            while (r1.next()) {
                a = r1.getInt("id");
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }

    public void saveStatistic(String name, String time, String activity) {
        Connection c = connectionDB.getConnection();
        try {
            try {
                Date date = new Date();
                Date date1 = new Date();
                int timeSec = getTime(time);
                date1.setTime(date.getTime() - timeSec * 1000);
                String year =new SimpleDateFormat("yyyy").format(date);
                String month = new SimpleDateFormat("MM").format(date);
                String day = new SimpleDateFormat("dd").format(date);
                String timeBegin = new SimpleDateFormat("HH:mm:ss").format(date1);
                String timeEnd = new SimpleDateFormat("HH:mm:ss").format(date);
                PreparedStatement statement = c.prepareStatement(
                        "INSERT INTO STATISTIC (ID,ACTIVITY,  NAME, TIME, YEAR, MONTH, DAY," +
                                "TIMEBEGIN, TIMEEND ) " +
                                "VALUES (?, ?, ?, ?, ?, ?,?,?,?)");
                statement.setInt(1, getMaxIdStatistic() + 1);
                statement.setString(2, activity);
                statement.setString(3, name);
                statement.setInt(4, timeSec);
                statement.setString(5, year);
                statement.setString(6, month);
                statement.setString(7, day);
                statement.setString(8, timeBegin);
                statement.setString(9, timeEnd);
                statement.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
                c.rollback();
            } finally {
                c.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getTime(String time) {
        int sec = Integer.parseInt(time.substring(6, 8));
        int minute = Integer.parseInt(time.substring(3, 5));
        int hour = Integer.parseInt(time.substring(0, 2));
        return sec + minute * 60 + hour * 3600;
    }

    public void writeUser(String name, String password) {
        //дата создания
        Connection c = connectionDB.getConnection();
        try {
            try {
                PreparedStatement statement = c.prepareStatement(
                        "INSERT INTO USERS (ID, NAME, PASSWORD, CREATEDATE) " +
                                "VALUES (?, ?, ?, ?)");
                statement.setInt(1, getMaxIdUsers() + 1);
                statement.setString(2, name);
                statement.setString(3, password);
                statement.setLong(4, new Date().getTime());
                statement.executeUpdate();
            } catch (SQLException ex) {
                c.rollback();
            } finally {
                c.setAutoCommit(true);
            }
        } catch (SQLException e) {
        }
    }

    public ArrayList<String> getActivityByName(String name) {
        ArrayList<String> list = new ArrayList<>();
        Connection c = connectionDB.getConnection();
        try {
            try {
                PreparedStatement statement = c.prepareStatement(
                        "SELECT * FROM ACTIVITY WHERE name = ?");
                statement.setString(1, name);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    list.add(rs.getString("activity"));
                }
            } catch (SQLException ex) {
                c.rollback();
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public void removeActivityFromActivity(String activity, String name) {
        Connection c = connectionDB.getConnection();
        try {
            try {
                Statement statement = c.createStatement();
                statement.executeUpdate("DELETE FROM ACTIVITY WHERE activity = '" + activity + "' AND name ='" + name + "'; ");
            } catch (SQLException ex) {
                ex.printStackTrace();
                c.rollback();
            } finally {
                c.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeActivityFromStatistic(String activity, String name) {
        Connection c = connectionDB.getConnection();
        try {
            try {
                Statement statement = c.createStatement();
                statement.executeUpdate("DELETE FROM statistic WHERE activity = '" + activity + "' AND name ='" + name + "'; ");
            } catch (SQLException ex) {
                ex.printStackTrace();
                c.rollback();
            } finally {
                c.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addActivity(String activity, String name) {
        Connection c = connectionDB.getConnection();
        try {
            try {
                PreparedStatement statement = c.prepareStatement(
                        "INSERT INTO ACTIVITY (ID, ACTIVITY, NAME) " +
                                "VALUES (?, ?, ?)");
                statement.setInt(1, getMaxIdActivity() + 1);
                statement.setString(2, activity);
                statement.setString(3, name);
                statement.executeUpdate();
            } catch (SQLException ex) {
                c.rollback();
            } finally {
                c.setAutoCommit(true);
            }
        } catch (SQLException e) {
        }
    }

    public boolean isExistActivity(String activity, String name){
        boolean isExist = true;
        PreparedStatement stmt = null;
        Connection c = connectionDB.getConnection();
        try {
            stmt = c.prepareStatement("SELECT * FROM activity WHERE name = ?" +
                    "and activity = '"+activity+"'");
            stmt.setString(1, name);
            ResultSet resultSet = stmt.executeQuery();
            if (!resultSet.next()) {
                isExist = false;
            }

        } catch (SQLException e) {
            isExist = false;
            e.printStackTrace();
        }
        return isExist;
    }
}

