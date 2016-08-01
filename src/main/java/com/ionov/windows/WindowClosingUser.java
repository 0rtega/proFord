package com.ionov.windows;

import com.ionov.NeedMethods;
import com.ionov.listeners.windowClosingUser.ActionListenerButtonCancel;
import com.ionov.listeners.windowClosingUser.ActionListenerButtonOk;

import javax.swing.*;
import java.awt.*;

/**
 * Created by user on 12.07.2016.
 */
public class WindowClosingUser {
    private JFrame window;
    private JDialog dialog;
    private NeedMethods need = NeedMethods.getNeed();

    public WindowClosingUser(JFrame window) {
        this.window = window;
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
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        JButton cancel =need.createButton("Отмена", 35, 70, 90, 35);
        cancel.addActionListener(new ActionListenerButtonCancel(dialog));
        panel.add(cancel);
        JButton ok = need.createButton("Ok", 180, 70, 90, 35);
        ok.addActionListener(new ActionListenerButtonOk(window, dialog));
        panel.add(ok);
        panel.add(need.createLabel("Вы уверены что хотите покинуть аккаунт??",20, 30, 300, 20,12,0 ));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        return panel;
    }


}
