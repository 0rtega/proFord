package com.ionov.windows;

import com.ionov.NeedMethods;
import javax.swing.*;
import java.awt.*;

/**
 * Created by user on 24.07.2016.
 */
public class WindowSave {
    private JFrame window;
    private JDialog dialog;
    private NeedMethods need = NeedMethods.getNeed();

    public WindowSave(JFrame window) {
        this.window = window;
        showFrame();
    }
    private void showFrame() {
        dialog = new JDialog(window, "Окно предупреждения");
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
        dialog.setResizable(false);
        dialog.setSize(320, 90);
        dialog.setLocationRelativeTo(null);
        dialog.add(createPanel());
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        panel.add(need.createLabel("Ваши данные были сохранены.",5, 15, 300, 20,14,0 ));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        return panel;
    }

    public void dispose(){
        dialog.dispose();
    }

}
