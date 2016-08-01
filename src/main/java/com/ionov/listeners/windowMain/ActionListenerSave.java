package com.ionov.listeners.windowMain;

import com.ionov.listeners.*;
import com.ionov.windows.WindowSave;
import com.ionov.workWithDB.WorkWithDB;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by user on 15.07.2016.
 */
public class ActionListenerSave implements ActionListener {
    private JFrame window;
    private JButton alarm;
    private JLabel timer;
    private JButton play;
    private JButton pause;
    private JButton save;
    private  JButton alarm1;
    private JButton reset;
    private JLabel select;
    private JLabel labelActivity;
    private String name;
    private WorkWithDB work = WorkWithDB.getWork();

    public ActionListenerSave(JFrame window, String name, JLabel labelActivity, JButton reset,
                              JLabel select, JButton alarm, JLabel timer,
                              JButton play, JButton pause, JButton save,
                              JButton alarm1) {
        this.alarm = alarm;
        this.name = name;
        this.window = window;
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
        String saveTime = timer.getText();
        work.saveStatistic(name, saveTime, labelActivity.getText());

        WindowSave windowSave = new WindowSave(window);

        Thread run = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                windowSave.dispose();
            }
        };
        run.start();
        play.setVisible(false);
        pause.setVisible(false);
        save.setVisible(false);
        reset.setVisible(false);
        select.setVisible(true);
        alarm1.setEnabled(true);
        Alarm.setStop(false);
        com.ionov.listeners.Timer.setStop(false);
        Alarm.setPauseTime("00:00:00");
        com.ionov.listeners.Timer.setPauseTime("00:00:00");
        alarm.setText("00:00:00");
        timer.setText("00:00:00");
        labelActivity.setText("-");
    }
}
