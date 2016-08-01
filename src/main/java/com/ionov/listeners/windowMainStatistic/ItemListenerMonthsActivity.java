package com.ionov.listeners.windowMainStatistic;

import com.ionov.NeedMethods;
import com.ionov.workWithDB.WorkWithDB;
import com.ionov.workWithStatistic.WorkWithStatistic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * Created by user on 26.07.2016.
 */
public class ItemListenerMonthsActivity implements ItemListener {
    private JLabel container1;
    private JLabel allTime;
    private JLabel allTime1;
    private JLabel ratioWatchTimeMonth;
    private JLabel ratioWatchTimeMonth1;
    private JLabel ratioCommonTimeMonth;
    private JLabel ratioCommonTimeMonth1;
    private static ArrayList<Integer> list;
    private NeedMethods need = NeedMethods.getNeed();
    private WorkWithDB work = WorkWithDB.getWork();
    private JComboBox<String> months1;
    private String name;
    private JComboBox<String> activityCombo;
    private WorkWithStatistic statistic = WorkWithStatistic.getStatistic();

    public ItemListenerMonthsActivity(JComboBox<String> activityCombo, String name, JComboBox<String> months1, JLabel allTime, JLabel allTime1, JLabel ratioWatchTimeMonth,
                                      JLabel ratioWatchTimeMonth1, JLabel ratioCommonTimeMonth,
                                      JLabel ratioCommonTimeMonth1, JLabel container1) {
        this.container1 = container1;
        this.activityCombo = activityCombo;
        this.name = name;
        this.months1 = months1;
        this.allTime = allTime;
        this.allTime1 = allTime1;
        this.ratioWatchTimeMonth = ratioWatchTimeMonth;
        this.ratioWatchTimeMonth1 = ratioWatchTimeMonth1;
        this.ratioCommonTimeMonth = ratioCommonTimeMonth;
        this.ratioCommonTimeMonth1 = ratioCommonTimeMonth1;
    }

    public static ArrayList<Integer> getList() {
        return list;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (months1.getSelectedItem() != null) {
            String tempMonth = (String) months1.getSelectedItem();
            String tempActivity = (String) activityCombo.getSelectedItem();
            container1.setVisible(true);
            allTime.setVisible(true);
            allTime1.setText(statistic.getTimeActivityMonth(this.name,
                    tempMonth, tempActivity));
            allTime1.setVisible(true);
            ratioWatchTimeMonth.setVisible(true);
            ratioWatchTimeMonth1.setText(statistic.getRatioWatchTimeMonth(this.name,
                    tempMonth, tempActivity));
            ratioWatchTimeMonth1.setVisible(true);
            ratioCommonTimeMonth.setVisible(true);
            ratioCommonTimeMonth1.setText(statistic.getRatioCommonTimeMonth(tempMonth, tempActivity, this.name));
            ratioCommonTimeMonth1.setVisible(true);

            if (months1.getSelectedItem() != null) {
                String data = (String) months1.getSelectedItem();
                String dateMonth = data.substring(5, 7);
                String dateYear = data.substring(0, 4);
                 list = work.getListDaysInMonthWhenWasActivity(name, dateMonth,
                        dateYear, (String) activityCombo.getSelectedItem());
                Collections.sort(list);
                Date date = new Date();
                date.setMonth(Integer.parseInt(dateMonth) - 1);
                date.setDate(1);
                int beginMonth = need.getBeginMonth(date);
                int longMonth = need.getLongMonth(date);
                Component[] compo = container1.getComponents();
                int day = 1;
                for (int i = 0; i < 42; i++) {
                    JButton button = (JButton) compo[i];
                    button.setBorder(null);
                    button.setVisible(false);
                    button.setEnabled(true);
                    button.setText(i + "");
                }
                for (int i = 0; i < longMonth; i++) {
                    JButton button = (JButton) compo[beginMonth - 1];
                    button.setVisible(true);
                    button.setText(day + "");
                    if (!list.contains(day)) {
                        button.setEnabled(false);
                    }
                    day++;
                    beginMonth++;
                }
            }
        }
    }
}
