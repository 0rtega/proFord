package com.ionov.listeners.windowCheckIn;

import com.ionov.windows.WindowMain;
import com.ionov.workWithDB.WorkWithDB;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionListenerCheckIn implements ActionListener {
    private JDialog dialog;
    private JFrame frame;
    private JTextField name;
    private JPasswordField password;
    private WorkWithDB work = WorkWithDB.getWork();

    public ActionListenerCheckIn(JFrame frame, JDialog dialog, JTextField name, JPasswordField password) {
        this.frame = frame;
        this.dialog = dialog;
        this.name = name;
        this.password = password;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        work.writeUser(name.getText(), String.valueOf(password.getPassword()));
        frame.dispose();
        dialog.dispose();
        new WindowMain(name.getText());
    }
}
