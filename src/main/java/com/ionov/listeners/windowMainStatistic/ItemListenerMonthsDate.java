package com.ionov.listeners.windowMainStatistic;

import com.ionov.NeedMethods;
import com.ionov.UTF8Control;
import com.ionov.workWithDB.WorkWithDB;
import com.ionov.workWithStatistic.MyTable;
import com.ionov.workWithStatistic.WorkWithStatistic;
import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.*;

/**
 * Created by user on 26.07.2016.
 */
public class ItemListenerMonthsDate implements ItemListener {
    private JLabel container;
    private JPanel panel;
    private JLabel title;
    private WorkWithDB work = WorkWithDB.getWork();
    private String name;
    private JComboBox<String> months;
    private NeedMethods need = NeedMethods.getNeed();
    private  static ArrayList<Integer> list;
    private WorkWithStatistic statistic = WorkWithStatistic.getStatistic();
    private Locale locale = new Locale("ru", "RU");
    private ResourceBundle res1 = ResourceBundle.getBundle("explain", locale, new UTF8Control());

    public static ArrayList<Integer> getList() {
        return list;
    }

    public ItemListenerMonthsDate(JPanel panel, String name, JComboBox<String> months, JLabel title,  JLabel container) {
        this.container = container;
        this.panel = panel;
        this.months = months;
        this.name = name;
        this.title= title;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        container.setVisible(true);
        title.setVisible(true);
        String data = (String)months.getSelectedItem();
        String dateMonth = data.substring(5,7);
        String dateYear = data.substring(0,4);
        list = work.getListDaysInMonthWhenWereActivities(name, dateMonth, dateYear);
        Collections.sort(list);

        MyTable myTable = new MyTable();
        myTable.setColumnName(statistic.getColumnNameDateWindow());
        myTable.setData(  statistic.getDataTableDateMonth(this.name, data));

        Component [] compo1 = panel.getComponents();
        for(Component i : compo1){
            if("tableDate".equals(i.getName())){
                panel.remove(i);
            }
        }

        JTable tableDate = new JTable(myTable);
        TableColumnModel model = tableDate.getColumnModel();
        model.getColumn(0).setPreferredWidth(25);
        model.getColumn(2).setPreferredWidth(60);
        JTableHeader tableHeader1 =  tableDate.getTableHeader();
        tableHeader1.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        tableHeader1.setPreferredSize(new Dimension(285, 35));
        tableHeader1.setFont(new Font("Times New Roman", Font.PLAIN, 11));
        tableHeader1.setToolTipText(res1.getString("tableDate"));

        JScrollPane scrollPane1 = new JScrollPane(tableDate);
        scrollPane1.setName("tableDate");
        scrollPane1.setBounds(10,250,273, 120);

        panel.add(scrollPane1);

        Date date = new Date();
        date.setMonth(Integer.parseInt(dateMonth) - 1);
        date.setDate(1);
        int beginMonth = need.getBeginMonth(date);
        int longMonth = need.getLongMonth(date);
        Component [] compo = container.getComponents();
        int day = 1;
        for(int i = 0; i< 42;i++){
            JButton button = (JButton) compo[i];
            button.setBorder(null);
            button.setVisible(false);
            button.setEnabled(true);
           button.setText(i + "");
        }
       for(int i = 0; i< longMonth; i++){
           JButton button = (JButton)compo[beginMonth - 1];
          button.setVisible(true);
          button.setText( day + "");
           if(!list.contains(day)){
               button.setEnabled(false);
           }
           day++;
           beginMonth++;
       }
    }




}
