package com.ionov.listeners.windowClosed;

import com.ionov.windows.WindowClosing;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


public class WindowListenerWindowClosed implements WindowListener {
    private JFrame window;
    private JLabel timer;
    private String name;
    private JLabel labelActivity;

    public WindowListenerWindowClosed(JFrame window) {
        this.window = window;
    }

    public WindowListenerWindowClosed(JLabel timer , String name, JLabel labelActivity, JFrame window) {
        this.window = window;
        this.timer = timer;
        this.labelActivity = labelActivity;
        this.name = name;
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        new WindowClosing(timer, name, labelActivity, window);
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
