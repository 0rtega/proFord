package com.ionov.listeners.windowStatisticActivity;

import com.ionov.listeners.windowMainStatistic.ItemListenerMonthsActivity;
import com.ionov.workWithStatistic.MyTable;
import com.ionov.workWithStatistic.WorkWithStatistic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by user on 27.07.2016.
 */
public class ActionListenerForward implements ActionListener {
    private JComboBox<String> month;
    private JComboBox<String> activityCombo;
    private JLabel date;
    private String name;
    private JPanel panel;
    private JButton back;
    private JButton forward;
    private JLabel allTime1;
    private JLabel ratioWatchTimeMonth1;
    private JLabel ratioCommonTimeMonth1;
    private WorkWithStatistic statistic = WorkWithStatistic.getStatistic();

    public ActionListenerForward(JPanel panel,JComboBox<String> activityCombo, JLabel allTime1, JLabel ratioWatchTimeMonth1, JLabel ratioCommonTimeMonth1,
                              JButton back, JButton forward, JComboBox<String> month, JLabel date,
                              String name) {
        this.month = month;
        this.back = back;
        this.activityCombo = activityCombo;
        this.allTime1 = allTime1;
        this.ratioCommonTimeMonth1 = ratioCommonTimeMonth1;
        this.ratioWatchTimeMonth1 = ratioWatchTimeMonth1;
        this.forward = forward;
        this.date = date;
        this.name = name;
       this. panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<Integer> list = ItemListenerMonthsActivity.getList();
        int dayBefore = Integer.parseInt(date.getText().substring(8,10));
        int index = list.indexOf(dayBefore);
        int dayNow = list.get(index + 1);
        String dateMonth =(String)month.getSelectedItem();
        String activityTemp = (String)activityCombo.getSelectedItem();
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

        Component[] compo1 = panel.getComponents();
        for(Component i : compo1){
            if("detailReport".equals(i.getName())){
                panel.remove(i);
            }
        }


        allTime1.setText(statistic.getTimeActivityDay(this.name, dateMonth, dayNow+"",activityTemp));
        ratioWatchTimeMonth1.setText(statistic.getRatioDayAndCommonWatchTime(this.name, dateMonth , dayNow + "", activityTemp));
        ratioCommonTimeMonth1.setText(statistic.getRatioDayCommonTimeDay(this.name, dateMonth, dayNow +"", activityTemp));

        MyTable myTable = new MyTable();
        myTable.setColumnName(statistic.getColumnNameDetailReportDays());
        myTable.setData(statistic.getDataTableDetailReportDayForOneActivity(name, dateMonth, ""+ dayNow, activityTemp));

        JTable detailReport = new JTable(myTable);
        detailReport.getTableHeader().setFont(new Font("Times New Roman", Font.PLAIN, 11));
        detailReport.getColumnModel().getColumn(0).setPreferredWidth(25);

        JScrollPane scrollPane2 = new JScrollPane(detailReport);
        scrollPane2.setName("detailReport");
        scrollPane2.setBounds(205, 60, 280, 155);
        panel.add(scrollPane2);

    }
}
