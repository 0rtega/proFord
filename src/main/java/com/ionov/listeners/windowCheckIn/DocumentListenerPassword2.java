package com.ionov.listeners.windowCheckIn;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by user on 09.07.2016.
 */
public class DocumentListenerPassword2 implements DocumentListener {

    private JLabel iconOk;
    private JLabel iconCancel;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JButton checkIn;
    private JLabel warn;
    private JLabel warn1;
    private static boolean truePassword2 = false;

    public DocumentListenerPassword2(JLabel warn1, JLabel warn, JButton checkIn, JLabel iconOk, JLabel iconCancel, JPasswordField passwordField1,
                                     JPasswordField passwordField2) {
        this.iconOk = iconOk;
        this.warn = warn;
        this.warn1 = warn1;
        this.checkIn = checkIn;
        this.iconCancel = iconCancel;
        this.passwordField1 = passwordField1;
        this.passwordField2 = passwordField2;

    }

    public static boolean isTruePassword2() {
        return truePassword2;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        warn();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        warn();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        warn();
    }

    private void warn() {
        Runnable doHighlight = new Runnable() {
            @Override
            public void run() {
                String password1 = String.valueOf(passwordField1.getPassword());
                String password2 = String.valueOf(passwordField2.getPassword());

                if (password2 == null || password2.equals("")) {
                    iconCancel.setVisible(false);
                    iconOk.setVisible(false);
                    warn.setForeground(Color.BLACK);
                    warn1.setVisible(false);
                    truePassword2 = false;
                } else {
                    if (password1.equals(password2)) {
                        iconCancel.setVisible(false);
                        warn.setForeground(Color.BLACK);
                        warn1.setVisible(false);
                        iconOk.setVisible(true);
                        truePassword2 = true;
                    } else {
                        iconOk.setVisible(false);
                        iconCancel.setVisible(true);
                        warn.setVisible(false);
                        warn1.setVisible(true);
                        truePassword2 = false;
                    }
                }
                if (DocumentListenerName.isTrueName() &&
                        DocumentListenerPassword1.isTruePassword1() &&
                        isTruePassword2()) {
                    checkIn.setEnabled(true);
                } else {
                    checkIn.setEnabled(false);
                }
            }
        };
        SwingUtilities.invokeLater(doHighlight);
    }
}
