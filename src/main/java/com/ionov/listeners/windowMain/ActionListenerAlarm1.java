package com.ionov.listeners.windowMain;

import com.ionov.listeners.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.ionov.listeners.Timer;

public class ActionListenerAlarm1 implements ActionListener {
    private static String time;
    private JButton alarm1;
    private JFrame window;
    private JLabel timer;


    public ActionListenerAlarm1( JLabel timer, JFrame window, JButton alarm1) {
        this.alarm1 = alarm1;
        this.timer = timer;
        this.window = window;
    }

    public static void setTime(String time) {
        ActionListenerAlarm1.time = time;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(time != null) {
            alarm1.setText(time);
            Alarm.setPauseTime(time);
            Alarm.setChange(true);
            if(!Alarm.isStop() & Timer.isStop() & !timer.getText().equals("00:00:00")){
                Alarm al = new Alarm(window, alarm1);
                al.setStop(true);
                al.start();
            }
        }
    }
}
