package com.ionov.listeners.windowAlarmClock;

import com.ionov.listeners.Alarm;
import com.ionov.listeners.windowMain.ActionListenerAlarm1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by user on 14.07.2016.
 */
public class ActionListenerSetup implements ActionListener {
    private JFrame window;
    private JDialog dialog;
    private JComboBox<Integer> hour;
    private JComboBox<Integer> minute;
    private JComboBox<Integer> sec;

    public ActionListenerSetup(JFrame window, JDialog dialog,
                               JComboBox<Integer> hour, JComboBox<Integer> minute,
                               JComboBox<Integer> sec) {
        this.window = window;
        this.dialog = dialog;
        this.hour = hour;
        this.minute = minute;
        this.sec = sec;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JLayeredPane panel = (JLayeredPane) window.getContentPane().getComponent(0);
        JPanel panel1 = null;
        for(Component i : panel.getComponents()){
            if("123".equals(i.getName()))
                panel1 = (JPanel) i;
        }
        JButton setup = null;
        String text = createTime();
        for(Component i: panel1.getComponents()){
            if("timer".equals(i.getName())) {
                JLabel timer = (JLabel) i;
                for(Component j : timer.getComponents()) {
                    if ("alarm1".equals(j.getName())) {
                        setup = (JButton) j;
                        setup.setText(text);
                    }
                }
            }
        }
        ActionListenerAlarm1.setTime(text);
        Alarm.setPauseTime(text);
        dialog.dispose();
    }
    private String createTime(){
        String time = "";
        int hour1  = (int)hour.getSelectedItem();
        int minute1  = (int)minute.getSelectedItem();
        int sec1  = (int)sec.getSelectedItem();
        if(hour1<10){
            time = time + 0 + hour1+ ":";
        }else{
            time = time + hour1+ ":";
        }
        if(minute1<10){
            time = time + 0 + minute1 + ":";
        }else{
            time = time + minute1+ ":";
        }
        if(sec1<10){
            time = time + 0 + sec1;
        }else{
            time = time + sec1;
        }
        return time;
    }
}
