package com.ionov.listeners.windowCheckIn;

import com.ionov.workWithDB.WorkWithDB;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DocumentListenerName implements DocumentListener {

    private JTextField login;
    private JLabel iconOk;
    private JLabel iconCancel;
    private JLabel warn1;
    private JLabel warn2;
    private JLabel title;
    private JButton checkIn;
    private static boolean trueName = false;
    private WorkWithDB work = WorkWithDB.getWork();

    public DocumentListenerName(JButton checkIn, JLabel title, JTextField login, JLabel iconOk, JLabel iconCancel, JLabel warn1, JLabel warn2) {
        this.title = title;
        this.checkIn = checkIn;
        this.login = login;
        this.iconOk = iconOk;
        this.iconCancel = iconCancel;
        this.warn1 = warn1;
        this.warn2 = warn2;
    }

    public static boolean isTrueName() {
        return trueName;
    }

    public void changedUpdate(DocumentEvent e) {
        warn();
    }

    public void removeUpdate(DocumentEvent e) {
        warn();
    }

    public void insertUpdate(DocumentEvent e) {
        warn();
    }

    public void warn() {

        Runnable doHighlight = new Runnable() {
            @Override
            public void run() {
                String tempName = login.getText();
                if (tempName == null || tempName.equals("")) {
                    iconOk.setVisible(false);
                    title.setVisible(true);
                    warn2.setVisible(false);
                    warn1.setVisible(false);
                    iconCancel.setVisible(false);
                    trueName = false;
                } else {
                    Pattern p = Pattern.compile("^[А-Яа-яA-Za-zёЁ0-9]+$");
                    Matcher m = p.matcher(tempName);
                    if (!m.matches()) {
                        //не проходит по условиям
                        title.setVisible(false);
                        warn2.setVisible(false);
                        iconOk.setVisible(false);
                        warn1.setVisible(true);
                        iconCancel.setVisible(true);
                        trueName = false;
                    } else {
                        if (work.isExistName(tempName)) {
                            //уже есть имя
                            title.setVisible(false);
                            warn1.setVisible(false);
                            iconOk.setVisible(false);
                            warn2.setVisible(true);
                            iconCancel.setVisible(true);
                            trueName = false;
                        } else {
                            //все хорошо появляется галочка
                            title.setVisible(true);
                            warn1.setVisible(false);
                            warn2.setVisible(false);
                            iconCancel.setVisible(false);
                            iconOk.setVisible(true);
                            trueName = true;
                        }
                    }
                }
                if (isTrueName() &&
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
