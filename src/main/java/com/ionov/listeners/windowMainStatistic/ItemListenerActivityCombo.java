package com.ionov.listeners.windowMainStatistic;

import com.ionov.workWithDB.WorkWithDB;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 26.07.2016.
 */
public class ItemListenerActivityCombo implements ItemListener {
    private JLabel con;
    private JComboBox<String> months1;
    private JComboBox<String> activityCombo;
    private String name;
    private JLabel container1;
    private JLabel allTime;
    private JLabel allTime1;
    private JLabel ratioWatchTimeMonth;
    private JLabel ratioWatchTimeMonth1;
    private JLabel ratioCommonTimeMonth;
    private JLabel ratioCommonTimeMonth1;
    private WorkWithDB work = WorkWithDB.getWork();

    public ItemListenerActivityCombo(String name, JComboBox<String> activityCombo, JLabel allTime, JLabel allTime1, JLabel ratioWatchTimeMonth,
                                     JLabel ratioWatchTimeMonth1, JLabel ratioCommonTimeMonth,
                                     JLabel ratioCommonTimeMonth1, JLabel container1, JLabel con, JComboBox<String> months1) {
        this.con = con;
        this.activityCombo =activityCombo;
        this.name = name;
        this.months1= months1;
        this.container1= container1;
        this.allTime = allTime;
        this.allTime1 = allTime1;
        this.ratioWatchTimeMonth = ratioWatchTimeMonth;
        this.ratioWatchTimeMonth1 = ratioWatchTimeMonth1;
        this.ratioCommonTimeMonth = ratioCommonTimeMonth;
        this.ratioCommonTimeMonth1 = ratioCommonTimeMonth1;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        con.setVisible(true);
        container1.setVisible(false);
        List<String> list = work.getListUniqueMonths(name, (String)activityCombo.getSelectedItem());
        months1.removeAllItems();
        months1.addItem(null);
        for(String i : list){
            months1.addItem(i);
        }
        months1.setSelectedItem(null);
        allTime.setVisible(false);
        allTime1.setVisible(false);
        ratioWatchTimeMonth.setVisible(false);
        ratioWatchTimeMonth1.setVisible(false);
        ratioCommonTimeMonth.setVisible(false);
        ratioCommonTimeMonth1.setVisible(false);
    }
}
