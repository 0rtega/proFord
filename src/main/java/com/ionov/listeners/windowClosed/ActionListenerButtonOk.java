package com.ionov.listeners.windowClosed;

import com.ionov.noRepeatEnter.NoRepeatEnter;
import com.ionov.windows.WindowEnterPassword;
import com.ionov.workWithDB.WorkWithDB;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by user on 11.07.2016.
 */
public class ActionListenerButtonOk implements ActionListener {
        private JFrame window;
        private JDialog dialog;
    private WorkWithDB work = WorkWithDB.getWork();
    private JLabel timer;
    private String name;
    private JLabel labelActivity;

        public ActionListenerButtonOk(JLabel timer , String name, JLabel labelActivity, JFrame window, JDialog dialog) {
            this.window = window;
            this.dialog = dialog;
            this.timer = timer;
            this.labelActivity = labelActivity;
            this.name = name;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(WindowEnterPassword.isBlocked()){
                //записать данные в бд
                work.saveStatistic(name, timer.getText(), labelActivity.getText());
            }
            window.dispose();
            dialog.dispose();
            new NoRepeatEnter().deleteFile();
        }
}
