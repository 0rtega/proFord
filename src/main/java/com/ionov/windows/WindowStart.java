package com.ionov.windows;

import com.ionov.NeedMethods;
import com.ionov.listeners.windowClosed.WindowListenerWindowClosed;
import com.ionov.listeners.windowStart.ActionListenerWindowStart;
import com.ionov.listeners.windowStart.MouseListenersWindowSrart;
import javax.swing.*;
import java.awt.*;

public class WindowStart {

    private JFrame windowStart;

    private NeedMethods need = NeedMethods.getNeed();

    public WindowStart() {
        showWindow();
    }

    private void showWindow() {
        windowStart = new JFrame("Добро пожаловать!");
        windowStart.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        windowStart.setVisible(true);
        windowStart.setResizable(false);
        windowStart.setSize(300, 400);
        windowStart.setLocationRelativeTo(null);
        windowStart.addWindowListener(new WindowListenerWindowClosed(windowStart));
        windowStart.add(createPanel());
        windowStart.repaint();
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setName("panelStart");
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        JButton button = need.createButton("Создать аккаунт",60, 150, 170, 40);
        button.addActionListener(new ActionListenerWindowStart(windowStart));
        panel.add(button);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        return panel;
    }

}
