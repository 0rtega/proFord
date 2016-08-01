package com.ionov.windows;

import com.ionov.NeedMethods;
import com.ionov.listeners.windowEnterPassword.ActionListenerCancel;
import com.ionov.listeners.windowEnterPassword.ActionListenerOk;

import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 * Created by user on 13.07.2016.
 */
public class WindowEnterPassword {

    private JFrame window;
    private JDialog dialog;
    private String name;
    private NeedMethods need = NeedMethods.getNeed();
    private static boolean isBlocked;

    public WindowEnterPassword(JFrame window, String name) {
        this.window = window;
        this.name = name;
        showWindow();
    }

    public static boolean isBlocked() {
        return isBlocked;
    }

    public static void setIsBlocked(boolean isBlocked) {
        WindowEnterPassword.isBlocked = isBlocked;
    }

    private void showWindow(){
        dialog = new JDialog(window, Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.setTitle("Проверка пароля");
        dialog.setBounds(0, 0, 250, 170);
        dialog.setResizable(false);
        dialog.getContentPane().add(createPanel());
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        dialog.repaint();
    }

    private JPanel createPanel(){
        JPanel panel = new JPanel();
        panel.setName("checkInPanel");
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        panel.add(need.createLabel(name + ", введите свой пароль:", 20,30, 200, 20, 12,0));
        JPasswordField password = need.createPassword(30, 60,180, 25);
        panel.add(password);

        JLabel warn = need.createLabel("Пароль введен неверно!", 20, 10, 200, 20, 10, 0);
        warn.setForeground(Color.RED);
        warn.setVisible(false);
        panel.add(warn);

        JButton ok = need.createButton("Ok", 30, 100, 80, 30);
        ok.addActionListener(new ActionListenerOk(name, dialog, window, warn, password));
        panel.add(ok);

        JButton cancel = need.createButton("Отмена", 130, 100, 80, 30);
        cancel.addActionListener(new ActionListenerCancel(dialog));
        panel.add(cancel);
        return panel;
    }

}
