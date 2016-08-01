package com.ionov.listeners.windowMainStatistic;

import com.ionov.NeedMethods;
import com.ionov.UTF8Control;
import com.ionov.workWithStatistic.MyTable;
import com.ionov.workWithStatistic.WorkWithStatistic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by user on 01.08.2016.
 */
public class ItemListenerContinueSelect implements ItemListener {
    private JPanel panel;
    private JButton selectDate;
    private JButton selectTime;
    private JLabel selectTitle;
    private String name;
    private static MyTable myTable;
    private JComboBox<String> activityCombo1;
    private NeedMethods need = NeedMethods.getNeed();
    private WorkWithStatistic statistic = WorkWithStatistic.getStatistic();
    private Locale locale = new Locale("ru", "RU");
    private ResourceBundle res1 = ResourceBundle.getBundle("explain", locale, new UTF8Control());

    public static MyTable getMyTable() {
        return myTable;
    }

    public ItemListenerContinueSelect(JComboBox<String> activityCombo1, String name, JPanel panel, JButton selectDate,
                                      JButton selectTime, JLabel selectTitle) {
        this.panel = panel;
        this.name = name;
        this.activityCombo1 = activityCombo1;
        this.selectDate = selectDate;
        this.selectTime = selectTime;
        this.selectTitle = selectTitle;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        need.removeJScrollPane(panel);
        selectTitle.setVisible(true);
        selectTime.setVisible(true);
        selectDate.setVisible(true);
        myTable = new MyTable();
        myTable.setColumnName(statistic.getListColumnNameSelect());
        String activityTemp = (String) activityCombo1.getSelectedItem();
        String timeTemp = (String) ((JComboBox<String>) e.getSource()).getSelectedItem();

        if (timeTemp != null) {

            if ("По дням".equals(timeTemp)) {
                myTable.setData(statistic.getDataTableSelectOneDay(this.name, activityTemp));
            }
            else  {
               myTable.setData(statistic.getDataTableSelectOneMonth(this.name, activityTemp));
            }
            panel.add(need.createJScrollPane(myTable));
        }
    }

}
