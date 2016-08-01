package com.ionov.listeners.windowMain;

import com.ionov.workWithDB.WorkWithDB;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by user on 20.07.2016.
 */
public class ActionListenerDelete implements ActionListener {
    private JLabel button;
    private JPanel content;
    private String name;
    private WorkWithDB work = WorkWithDB.getWork();


    public ActionListenerDelete(String name, JPanel content, JLabel button) {
        this.button = button;
        this.content = content;
        this.name = name;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        content.remove(button);
        content.repaint();
        work.removeActivityFromActivity(button.getText(), name);
        work.removeActivityFromStatistic(button.getText(), name);
    }
}
