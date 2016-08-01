package com.ionov.windows;

import com.ionov.NeedMethods;
import com.ionov.listeners.windowAppWorked.ActionListenerWindowAppWorked;
import com.ionov.listeners.windowClosed.ActionListenerButtonCancel;
import com.ionov.listeners.windowClosed.ActionListenerButtonOk;
import com.ionov.listeners.windowStart.MouseListenersWindowSrart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Created by user on 11.07.2016.
 */
public class WindowClosing {
   private JFrame window;
    private JDialog dialog;
    private NeedMethods need = NeedMethods.getNeed();
    private JLabel timer;
    private String name;
    private JLabel labelActivity;

    public WindowClosing(JLabel timer , String name, JLabel labelActivity, JFrame window) {
        this.window = window;
        this.timer = timer;
        this.labelActivity = labelActivity;
        this.name = name;
        showFrame();
    }
    private void showFrame() {
        dialog = new JDialog(window, "Окно подтверждения");
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
        dialog.setResizable(false);
        dialog.setSize(320, 160);
        dialog.setLocationRelativeTo(null);
        dialog.add(createPanel());
        dialog.repaint();
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        JButton cancel =need.createButton("Отмена", 35, 70, 90, 35);
        cancel.addActionListener(new ActionListenerButtonCancel(dialog));
        panel.add(cancel);
        JButton ok = need.createButton("Ok", 180, 70, 90, 35);
        ok.addActionListener(new ActionListenerButtonOk(timer, name, labelActivity, window, dialog));
        panel.add(ok);
        panel.add(need.createLabel("Вы уверены что хотите покинуть программу?",20, 30, 300, 20,12,0 ));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        return panel;
    }


}
