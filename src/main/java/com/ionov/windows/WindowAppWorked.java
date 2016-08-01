package com.ionov.windows;

import com.ionov.NeedMethods;
import com.ionov.listeners.windowAppWorked.ActionListenerWindowAppWorked;
import com.ionov.listeners.windowStart.ActionListenerWindowStart;
import com.ionov.listeners.windowStart.MouseListenersWindowSrart;

import javax.swing.*;
import java.awt.*;

public class WindowAppWorked {

    private JFrame windowStart;
    private NeedMethods need = NeedMethods.getNeed();

    public WindowAppWorked() {
        showWindow();
    }

    private void showWindow() {
        windowStart = new JFrame("Ошибка!");
        windowStart.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        windowStart.setVisible(true);
        windowStart.setResizable(false);
        windowStart.setSize(250, 160);
        windowStart.setLocationRelativeTo(null);
        windowStart.add(createPanel());
        windowStart.repaint();
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        JButton button = need.createButton("Ok", 85, 70, 70, 35);
        button.addActionListener(new ActionListenerWindowAppWorked(windowStart));
        panel.add(button);
        panel.add(need.createLabel("Приложение уже работает!", 25, 30, 200, 20,12,0));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        return panel;
    }

    public static void main(String[] args) {
        new WindowAppWorked();
    }

}
