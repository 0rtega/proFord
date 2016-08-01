package com.ionov.listeners.windowMain;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.ionov.listeners.Alarm;
import com.ionov.listeners.Timer;

/**
 * Created by user on 15.07.2016.
 */
public class ActionListenerPlay implements ActionListener {
    private JLabel timer;
    private JButton play;
    private JButton pause;
    private JButton alarm;
    private JButton alarm1;
    private JButton save;
    private JFrame window;

    public ActionListenerPlay(JFrame window, JButton alarm1, JButton save, JButton alarm, JLabel timer, JButton pause, JButton play) {
        this.timer = timer;
        this.window = window;
        this.alarm1 = alarm1;
        this.save = save;
        this.alarm =alarm;
        this.play = play;
        this.pause = pause;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Timer.setStop(true);
        Alarm.setStop(true);
        if(!alarm.getText().equals("00:00:00")) {
            new Alarm(window, alarm).start();
        }
        new Timer(timer).start();
        play.setVisible(false);
        pause.setVisible(true);
        alarm1.setEnabled(false);
        save.setEnabled(false);
    }
}
