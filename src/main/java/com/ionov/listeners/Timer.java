package com.ionov.listeners;

import javax.swing.*;

/**
 * Created by user on 09.07.2016.
 */
public class Timer extends Thread {
    private JLabel timer;
    private static boolean stop = true;
    private static String pauseTime;
    private int hour ;
    private int minute ;
    private int sec ;
    public Timer (JLabel timer){
        this.timer = timer;
    }


    public static boolean isStop() {
        return stop;
    }

    public static void setStop(boolean stop) {
        Timer.stop = stop;
    }

    public static String getPauseTime() {
        return pauseTime;
    }

    public static void setPauseTime(String pauseTime) {
        Timer.pauseTime = pauseTime;
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
            time = timer.getText();
        }
        sec = Integer.parseInt(time.substring(6,8));
        minute = Integer.parseInt(time.substring(3,5));
        hour = Integer.parseInt(time.substring(0,2));
        while (stop) {
            timer.setText(toString());
            pauseTime = toString();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(sec < 59){
                sec = sec + 1;
            }else{
                sec = 0;
                if(minute < 59){
                    minute = minute + 1;
                }else{
                    minute = 0;
                    hour = hour + 1;
                }
            }
        }
    }
}
