package com.ionov.listeners.windowMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import static java.awt.Frame.ICONIFIED;
import static java.awt.Frame.MAXIMIZED_BOTH;
import static java.awt.Frame.NORMAL;

/**
 * Created by user on 28.07.2016.
 */
public class WidowStateListener implements WindowStateListener {
    private JFrame window;
    private SystemTray tray;
    private TrayIcon trayIcon;

    public WidowStateListener(JFrame window, SystemTray tray, TrayIcon trayIcon) {
        this.window = window;
        this.tray = tray;
        this.trayIcon = trayIcon;
    }

    @Override
    public void windowStateChanged(WindowEvent e) {
        if (e.getNewState() == ICONIFIED) {
            try {
                tray.add(trayIcon);
                window.setVisible(false);
            } catch (AWTException ex) {
            }
        }
        if (e.getNewState() == 7) {
            window.setVisible(false);
        }
        if (e.getNewState() == MAXIMIZED_BOTH) {
            tray.remove(trayIcon);
            window.setVisible(true);
        }
        if (e.getNewState() == NORMAL) {
            tray.remove(trayIcon);
            window.setVisible(true);
        }
    }
}
