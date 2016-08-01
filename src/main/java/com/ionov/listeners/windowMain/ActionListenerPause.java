package com.ionov.listeners.windowMain;

import com.ionov.listeners.*;
import com.ionov.listeners.Timer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by user on 15.07.2016.
 */
public class ActionListenerPause implements ActionListener {
    private JButton play;
    private JButton pause;
    private JButton alarm1;
    private JButton save;
    private JButton reset;


    public ActionListenerPause(JButton reset, JButton alarm1, JButton save,  JButton play, JButton pause) {
        this.play = play;
        this.reset = reset;
        this.pause = pause;
        this.alarm1 = alarm1;
        this.save = save;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        play.setVisible(true);
        pause.setVisible(false);
        Alarm.setStop(false);
        Timer.setStop(false);
        alarm1.setEnabled(true);
        save.setEnabled(true);
        reset.setEnabled(true);
    }
}
