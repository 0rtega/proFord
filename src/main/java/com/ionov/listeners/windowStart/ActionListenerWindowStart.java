package com.ionov.listeners.windowStart;

import com.ionov.windows.WindowCheckIn;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by user on 09.07.2016.
 */
public class ActionListenerWindowStart implements ActionListener {
    private JFrame windowStart;

    public ActionListenerWindowStart(JFrame windowStart) {
        this.windowStart = windowStart;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       new WindowCheckIn(windowStart);
    }
}
