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
public class DocumentListenerPassword1 implements DocumentListener {
    private JLabel iconOk;
    private JLabel iconCancel;
    private JLabel warn;
    private JPasswordField passwordField;
    private JButton checkIn;
    private static boolean truePassword1 = false;

    public DocumentListenerPassword1(JButton checkIn, JPasswordField passwordField, JLabel iconOk, JLabel iconCancel, JLabel warn) {
        this.passwordField = passwordField;
        this.iconOk = iconOk;
        this.checkIn = checkIn;
        this.iconCancel = iconCancel;
        this.warn = warn;
    }

    public static boolean isTruePassword1() {
        return truePassword1;
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
                String tempName = String.valueOf(passwordField.getPassword());
                Pattern p = Pattern.compile("^[А-Яа-яA-Za-zёЁ0-9!@#%^\\-/&*()=_+}:?>\"\\[\\]\\{\\}<№;\\.\\, ]+$");
                Matcher m = p.matcher(tempName);

                if (tempName == null || tempName.equals("")) {
                    iconCancel.setVisible(false);
                    iconOk.setVisible(false);
                    warn.setForeground(Color.BLACK);
                    truePassword1 = false;
                } else {
                    if (m.matches() & tempName.length() > 6) {
                        iconCancel.setVisible(false);
                        warn.setForeground(Color.BLACK);
                        iconOk.setVisible(true);
                        truePassword1 = true;
                    } else {
                        iconOk.setVisible(false);
                        iconCancel.setVisible(true);
                        warn.setForeground(Color.RED);
                        truePassword1 = false;
                    }
                }
                if (DocumentListenerName.isTrueName() &&
                        DocumentListenerPassword1.isTruePassword1() &&
                        DocumentListenerPassword2.isTruePassword2())
                {
                    checkIn.setEnabled(true);
                } else {
                    checkIn.setEnabled(false);
                }
            }
        };
        SwingUtilities.invokeLater(doHighlight);
    }
}
