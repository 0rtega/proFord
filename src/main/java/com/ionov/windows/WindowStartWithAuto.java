package com.ionov.windows;

import com.ionov.NeedMethods;
import com.ionov.listeners.windowClosed.WindowListenerWindowClosed;
import com.ionov.listeners.windowStartWithAuto.ActionListenerCheckIn;
import com.ionov.listeners.windowStartWithAuto.ActionListenerEnter;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

//фрейм с окном входа с авторизацией
public class WindowStartWithAuto {

    private JFrame windowAuto;
    private NeedMethods need = NeedMethods.getNeed();
    private ResourceBundle res = ResourceBundle.getBundle("paths");

    public WindowStartWithAuto() {
        showWindow();
    }

    private void showWindow() {
        windowAuto = new JFrame("Добро пожаловать!");
        windowAuto.setName("qqq");
        windowAuto.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        windowAuto.setVisible(true);
        windowAuto.setSize(300, 400);
        windowAuto.setResizable(false);
        windowAuto.setLocationRelativeTo(null);
        windowAuto.addWindowListener(new WindowListenerWindowClosed(windowAuto));
        windowAuto.add(createPanel());
        windowAuto.repaint();
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setName("12");
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        JButton checkIn = need.createButtonWithIcon(res.getString("user2"), 0, 0, 50, 50);
        checkIn.addActionListener(new ActionListenerCheckIn(windowAuto));
        panel.add(checkIn);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        JButton cancel = need.createButtonWithIcon(res.getString("close"), 244, 0,50,50 );
        cancel.setEnabled(false);
        panel.add(cancel);
        JLabel label = need.createLabel("ProForD", 49,0, 196, 50,15, 0);
        label.setFont(new Font("Arial", Font.BOLD, 30));
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panel.add(label);

        panel.add(need.createLabel("логин", 0, 130, 70, 25, 13, 4));
        JTextField login = need.createTextField(80, 130, 160, 25);
        login.setName("login");

        panel.add(login);
        panel.add(need.createLabel("пароль", 0, 160, 70, 25, 13, 4));
        JPasswordField passwordField = need.createPassword(80, 160, 160, 25);
        passwordField.setName("password");
        panel.add(passwordField);
        JLabel warnLabel = need.createLabel("Неверный логин или пароль!", 80, 90, 150, 20,11,0);
        warnLabel.setForeground(Color.RED);
        warnLabel.setVisible(false);
        panel.add(warnLabel);
        JButton enter = need.createButton("Войти на ProForD", 80, 200, 160, 30);
        enter.addActionListener(new ActionListenerEnter(passwordField, login, warnLabel, windowAuto));
        panel.add(enter);

        return panel;
    }

    public static void main(String[] args) {
        new WindowStartWithAuto();
    }
}
