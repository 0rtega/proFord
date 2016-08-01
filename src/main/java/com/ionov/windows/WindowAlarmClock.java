package com.ionov.windows;

import com.ionov.NeedMethods;
import com.ionov.listeners.windowAlarmClock.ActionListenerCancel;
import com.ionov.listeners.windowAlarmClock.ActionListenerSetup;

import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class WindowAlarmClock {

    private JFrame window;
    private JDialog dialog;
    private NeedMethods need = NeedMethods.getNeed();

    public WindowAlarmClock(JFrame window) {
        this.window = window;
        showDialog();
    }

    private void showDialog() {
        dialog = new JDialog(window, Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.setTitle("Напоминание");
        dialog.setBounds(0, 0, 250, 170);
        dialog.setResizable(false);
        dialog.getContentPane().add(createPanel());
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        dialog.repaint();
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        JComboBox<Integer> hour = need.createComboBox(10,30,50,25, 0,11);
        JComboBox<Integer> minute = need.createComboBox(90,30,50,25, 0,59);
        JComboBox<Integer> sec = need.createComboBox(170,30,50,25, 0,60);
        panel.add(need.createLabel("Ч", 60,30,15,25,17,0));
        panel.add(need.createLabel("М", 140,30,18,25,17,0));
        panel.add(need.createLabel("С", 220,30,15,25,17,0));
        JButton cancel = need.createButton("Отмена", 10, 90, 90, 30);
        cancel.addActionListener(new ActionListenerCancel(dialog));
        JButton setup = need.createButton("Установить", 130, 90, 90, 30);
        setup.addActionListener(new ActionListenerSetup(window, dialog, hour,minute,sec));
        panel.add(cancel);
        panel.add(setup);
        panel.add(minute);
        panel.add(sec);
        panel.add(hour);
        return panel;
    }

}
