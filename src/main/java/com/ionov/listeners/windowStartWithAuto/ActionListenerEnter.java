package com.ionov.listeners.windowStartWithAuto;

import com.ionov.windows.WindowMain;
import com.ionov.workWithDB.WorkWithDB;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionListenerEnter implements ActionListener {
    private JLabel warn;
    private JTextField name;
    private JPasswordField password;
    private JFrame window;

    public ActionListenerEnter(JPasswordField password, JTextField name, JLabel warn, JFrame window) {
        this.password = password;
        this.name = name;
        this.warn = warn;
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name1 = name.getText();
        String password1 = String.valueOf(password.getPassword());
        if(WorkWithDB.getWork().isExistName(name1)){
            if(WorkWithDB.getWork().getPassword(name1).equals(password1)){
                window.dispose();
                new WindowMain(name1);
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
                run.start();
            }
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
