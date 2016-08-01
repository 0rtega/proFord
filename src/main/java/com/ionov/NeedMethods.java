package com.ionov;

import com.ionov.listeners.windowStart.MouseListenersWindowSrart;
import com.ionov.workWithStatistic.DataTableSelect;
import com.ionov.workWithStatistic.MyTable;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by user on 12.07.2016.
 */
public class NeedMethods {

    private static NeedMethods need = new NeedMethods();
    private Locale locale = new Locale("ru", "RU");
    private ResourceBundle res1 = ResourceBundle.getBundle("explain", locale, new UTF8Control());


    private NeedMethods() {
    }

    public static NeedMethods getNeed() {
        return need;
    }


    public JButton createButton(String name, int a, int b, int c, int d) {
        JButton button = new JButton(name);
        button.setName(name);
        button.setBounds(a, b, c, d);
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Times New Roman", Font.PLAIN, 11));
        button.setText(name);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        button.setFocusPainted(false);
        button.addMouseListener(new MouseListenersWindowSrart(button));
        return button;
    }

    public JButton createButton1(String name, int a, int b, int c, int d) {
        JButton button = new JButton(name);
        button.setName(name);
        button.setBounds(a, b, c, d);
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        button.setText(name);
        button.setFocusPainted(false);
        return button;
    }

    public JPasswordField createPassword(int a, int b, int c, int d) {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        passwordField.setBounds(a, b, c, d);
        return passwordField;
    }

    public JTextField createTextField(int a, int b, int c, int d) {
        JTextField textField = new JTextField();
        textField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        textField.setBounds(a, b, c, d);
        return textField;
    }

    public JLabel createLabel(String name, int a, int b, int c, int d, int font, int cons) {
        JLabel label = new JLabel(name);
        label.setName(name);
        label.setBounds(a, b, c, d);
        label.setFont(new Font("Arial", Font.PLAIN, font));
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setHorizontalAlignment(cons);
        return label;
    }

    public JLabel createLabel(String name, int a, int b, int c, int d, int font, int cons, int border) {
        JLabel label = new JLabel(name);
        label.setName(name);
        label.setBounds(a, b, c, d);
        label.setFont(new Font("Arial", Font.PLAIN, font));
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setHorizontalAlignment(cons);
        label.setBorder(BorderFactory.createEtchedBorder());
        return label;
    }


    public JButton createButtonWithIcon(String path, int a, int b, int c, int d) {
        JButton createUser = new JButton();
        createUser.setBounds(a, b, c, d);
        createUser.setName(path);
        createUser.setIcon(new ImageIcon(path));
        createUser.setBackground(Color.WHITE);
        createUser.setForeground(Color.BLACK);
        createUser.setFont(new Font("Arial", Font.PLAIN, 12));
        createUser.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        createUser.setFocusPainted(false);
        createUser.addMouseListener(new MouseListenersWindowSrart(createUser));
        return createUser;
    }

    public JLabel createIcon(int a, int b, int c, int d, String path) {
        JLabel label = new JLabel();
        label.setBounds(a, b, c, d);
        label.setIcon(new ImageIcon(path));
        return label;
    }

    public JLabel createStar(int a, int b, int c, int d) {
        JLabel label = new JLabel("*");
        label.setBounds(a, b, c, d);
        label.setForeground(Color.RED);
        return label;
    }

    public JComboBox<Integer> createComboBox(int a, int b, int c, int d, int begin, int end) {
        JComboBox<Integer> combo = new JComboBox<>();
        combo.setBounds(a, b, c, d);
        combo.setBackground(Color.WHITE);
        combo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        combo.setAutoscrolls(true);
        for (int i = begin; i <= end; i++) {
            combo.addItem(i);
        }
        return combo;
    }

    public int getLongMonth(Date date) {
        switch (new SimpleDateFormat("MMMM").format(date)) {
            case "Январь":
                return 31;
            case "Февраль":
                return 28;
            case "Март":
                return 31;
            case "Апрель":
                return 30;
            case "Май":
                return 31;
            case "Июнь":
                return 30;
            case "Июль":
                return 31;
            case "Август":
                return 31;
            case "Сентябрь":
                return 30;
            case "Октябрь":
                return 31;
            case "Ноябрь":
                return 30;
            case "Декабрь":
                return 31;
            default:
                return 0;
        }
    }

    public String getTimeString(int commonTimeActivities) {
        int hour = commonTimeActivities / 3600;
        commonTimeActivities = commonTimeActivities % 3600;
        int minute = commonTimeActivities / 60;
        commonTimeActivities = commonTimeActivities % 60;
        int sec = commonTimeActivities;
        String a = "";
        if (hour < 10) {
            a = "0" + hour;
        } else a = hour + "";
        if (minute < 10) {
            a = a + ":0" + minute;
        } else a = a + ":" + minute;
        if (sec < 10) {
            a = a + ":0" + sec;
        } else a = a + ":" + sec;
        return a;
    }

    public int getBeginMonth(Date date) {
        switch (new SimpleDateFormat("EEEE").format(date)) {
            case "понедельник":
                return 1;
            case "вторник":
                return 2;
            case "среда":
                return 3;
            case "четверг":
                return 4;
            case "пятница":
                return 5;
            case "суббота":
                return 6;
            case "воскресенье":
                return 7;
            default:
                return 0;
        }
    }

    public JScrollPane createJScrollPane(MyTable myTable, String toolText, int oneCol, int twoCol, String name,
                                         int a, int b, int c, int d) {
        JTable selectTable = new JTable(myTable);
        JTableHeader tableHeader = selectTable.getTableHeader();
        tableHeader.setPreferredSize(new Dimension(285, 35));
        tableHeader.setToolTipText(res1.getString(toolText));
        tableHeader.setFont(new Font("Times New Roman", Font.PLAIN, 11));
        selectTable.getColumnModel().getColumn(0).setPreferredWidth(oneCol);
        selectTable.getColumnModel().getColumn(2).setPreferredWidth(twoCol);
        JScrollPane selectScroll = new JScrollPane(selectTable);
        selectScroll.setName(name);
        selectScroll.setBounds(a, b, c, d);
        return selectScroll;
    }

    public void removeJScrollPane(JPanel panel, String name) {
        Component[] compo1 = panel.getComponents();
        for (Component i : compo1) {
            if (name.equals(i.getName())) {
                panel.remove(i);
            }
        }
        panel.repaint();
    }
}
