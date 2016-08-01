package com.ionov.listeners.windowStatisticDays;

import com.ionov.NeedMethods;
import com.ionov.UTF8Control;
import com.ionov.listeners.windowMainStatistic.ItemListenerMonthsDate;
import com.ionov.workWithStatistic.MyTable;
import com.ionov.workWithStatistic.WorkWithStatistic;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by user on 27.07.2016.
 */
public class ActionListenerForward implements ActionListener {
    private JComboBox<String> month;
    private JLabel date;
    private String name;
    private JPanel panel;
    private JButton back;
    private JButton forward;
    private NeedMethods need = NeedMethods.getNeed();
    private WorkWithStatistic statistic = WorkWithStatistic.getStatistic();
    private Locale locale = new Locale("ru", "RU");
    private ResourceBundle res1 = ResourceBundle.getBundle("explain", locale, new UTF8Control());

    public ActionListenerForward(JPanel panel, JButton back, JButton forward,JComboBox<String> month, JLabel date,
                              String name) {
        this.month = month;
        this.date = date;
        this.back = back;
        this.panel = panel;
        this.forward = forward;
        this.name = name;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<Integer> list = ItemListenerMonthsDate.getList();
        int dayBefore = Integer.parseInt(date.getText().substring(8,10));
        int index = list.indexOf(dayBefore);
        int dayNow = list.get(index + 1);
        String dateMonth =(String)month.getSelectedItem();
        date.setText(dateMonth + "-" + dayNow);

        if(list.indexOf(dayNow) == 0){
            back.setEnabled(false);
        }else{
            back.setEnabled(true);
        }

        if(list.indexOf(dayNow) == list.size() -1){
            forward.setEnabled(false);
        }else{
            forward.setEnabled(true);
        }

        need.removeJScrollPane(panel,"commonReport");
        need.removeJScrollPane(panel,"detailReport");

        MyTable myTable = new MyTable();
        myTable.setColumnName(statistic.getColumnNameReportCommonDays());
        myTable.setData(statistic.getDataTableCommonReportDay(name, dateMonth, dayNow+""));

        JScrollPane commonReportJScrolle1 =need.createJScrollPane(myTable,"commonTable",25,60,
                "commonReport", 10,60,285,155);

        panel.add(commonReportJScrolle1);

        MyTable myTable1 = new MyTable();
        myTable1.setColumnName(statistic.getColumnNameDetailReportDays());
        myTable1.setData( statistic.getDataTableDetailReportDay(name, dateMonth, ""+dayNow));

        JScrollPane scrollPane2 = need.createJScrollPane(myTable1,"detailReport", 25,80,
                "detailReport", 305,60,280,155);
        panel.add(scrollPane2);
    }
}
