package com.ionov.listeners.windowEnterPassword;

import com.ionov.windows.WindowEnterPassword;
import com.ionov.windows.WindowMain;
import com.ionov.workWithDB.WorkWithDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * Created by user on 13.07.2016.
 */
public class ActionListenerOk implements ActionListener {
    private JDialog dialog;
    private JFrame window;
    private JLabel warn;
    private JPasswordField password;
    private String name;

    private WorkWithDB work = WorkWithDB.getWork();

    public ActionListenerOk(String name, JDialog dialog, JFrame window, JLabel warn, JPasswordField password) {
        this.dialog = dialog;
        this.window = window;
        this.warn = warn;
        this.name = name;
        this.password = password;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(String.valueOf(password.getPassword()).equals(work.getPassword(name))){
            dialog.dispose();
            for(Map.Entry<Component, Boolean> i : WindowMain.getIsVisible().entrySet()){
                i.getKey().setVisible(i.getValue());
            }
            WindowMain.cleanHashMap();
            WindowEnterPassword.setIsBlocked(false);
        }else{
            Thread run = new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    warn.setVisible(false);
                }
            };
            warn.setVisible(true);
            password.setText("");
            run.start();
        }
    }
}
