package com.ionov.listeners.windowMain;

import com.ionov.windows.WindowEnterPassword;
import com.ionov.windows.WindowMain;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionListenerLock implements ActionListener{
    private JFrame window;

    public ActionListenerLock(JFrame window) {
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JLayeredPane panel = (JLayeredPane) window.getContentPane().getComponent(0);
        JPanel panel1 = null;
        for(Component i : panel.getComponents()){
            WindowMain.addHashMap(i, i.isVisible());
            if("123".equals(i.getName())) {
                panel1 = (JPanel) i;
            }else{
                i.setVisible(false);
            }
        }
        for(Component i: panel1.getComponents()){
            WindowMain.addHashMap(i, i.isVisible());
            if("unlock".equals(i.getName())){
                i.setVisible(true);
            }else{
                i.setVisible(false);
            }
        }
        WindowEnterPassword.setIsBlocked(true);
    }
}
