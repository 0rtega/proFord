package com.ionov.listeners.windowMain;

import com.ionov.listeners.Alarm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.ionov.listeners.Timer;

/**
 * Created by user on 15.07.2016.
 */
public class ActionListenerReset implements ActionListener {
    private JButton alarm;
    private JLabel timer;
    private JButton play;
    private JButton pause;
    private JButton save;
    private  JButton alarm1;
    private JButton reset;
    private JLabel select;
    private JLabel labelActivity;


    public ActionListenerReset(JLabel labelActivity, JButton reset, JLabel select,
                               JButton alarm, JLabel timer, JButton play, JButton pause,
                               JButton save, JButton alarm1) {
        this.alarm = alarm;
        this.timer = timer;
        this.play = play;
        this.pause = pause;
        this.reset = reset;
        this.select = select;
        this.save = save;
        this.alarm1 = alarm1;
        this.labelActivity = labelActivity;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        play.setVisible(false);
        pause.setVisible(false);
        save.setVisible(false);
        reset.setVisible(false);
        select.setVisible(true);
        alarm.setEnabled(true);
        ActionListenerAlarm1.setTime("00:00:00");
        Alarm.setStop(false);
        Timer.setStop(false);
        Alarm.setPauseTime("00:00:00");
        Timer.setPauseTime("00:00:00");
        alarm1.setText("00:00:00");
        timer.setText("00:00:00");
        labelActivity.setText("-");
    }
}
