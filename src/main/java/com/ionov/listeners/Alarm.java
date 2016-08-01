package com.ionov.listeners;

import javax.swing.*;
import java.awt.*;

/**
 * Created by user on 15.07.2016.
 */
public class Alarm extends Thread {
    private  JButton alarm;
    private JFrame window;
    private static String pauseTime;
    private static boolean stop = true;
    private static boolean change = false;
    private int sec;
    private int minute;
    private int hour;

    public Alarm(JFrame window, JButton button){
        this.alarm = button;
        this.window = window;
    }

    public static void setChange(boolean change) {
        Alarm.change = change;
    }

    public static void setPauseTime(String pauseTime) {
        Alarm.pauseTime = pauseTime;
    }

    public static void setStop(boolean stop) {
        Alarm.stop = stop;
    }

    public static boolean isStop() {
        return stop;
    }

    @Override
    public String toString() {
        String hour1 = "";
        if(hour < 10){
            hour1 = "0" + hour;
        }else{
            hour1 = "" + hour;
        }
        String minute1 = "";
        if(minute < 10){
            minute1 = "0" + minute;
        }else{
            minute1 = "" + minute;
        }
        String sec1 = "";
        if(sec < 10){
            sec1 = "0" + sec;
        }else{
            sec1 = "" + sec;
        }
        return hour1 +
                ":" + minute1 +
                ":" + sec1;
    }

    @Override
    public void run() {
        String time;
        if(pauseTime != null ) {
            time = pauseTime;
        }else {
            time = alarm.getText();
        }
        sec = Integer.parseInt(time.substring(6,8));
        minute = Integer.parseInt(time.substring(3,5));
        hour = Integer.parseInt(time.substring(0,2));
        while (stop) {
            if(change) {
                change = false;
                String time1;
                if(pauseTime != null ) {
                    time1 = pauseTime;
                }else {
                    time1 = alarm.getText();
                }
                sec = Integer.parseInt(time1.substring(6,8));
                minute = Integer.parseInt(time1.substring(3,5));
                hour = Integer.parseInt(time1.substring(0,2));
            }else {
                alarm.setText(toString());
                pauseTime = toString();
            }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (sec > 0) {
                    sec = sec - 1;
                } else {
                    if (minute > 0) {
                        sec = 59;
                        minute = minute - 1;
                    } else {
                        if (hour > 0) {
                            minute = 59;
                            hour = hour - 1;
                        } else {
                            stop = false;
                            //звук и выход из трея если он там
                            window.setVisible(true);
                            Toolkit.getDefaultToolkit().beep();
                            Thread run = new Thread() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(500);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    Toolkit.getDefaultToolkit().beep();
                                }
                            };
                            run.start();
                        }
                    }

            }
        }
    }
}
