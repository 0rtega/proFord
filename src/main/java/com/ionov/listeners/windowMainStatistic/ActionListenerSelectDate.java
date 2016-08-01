package com.ionov.listeners.windowMainStatistic;

import com.ionov.NeedMethods;
import com.ionov.UTF8Control;
import com.ionov.workWithStatistic.DataTableSelect;
import com.ionov.workWithStatistic.MyTable;
import com.ionov.workWithStatistic.Statistic;
import com.ionov.workWithStatistic.WorkWithStatistic;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Created by user on 26.07.2016.
 */
public class ActionListenerSelectDate implements ActionListener {
    private static int count = 0;
    private JPanel panel;
    private WorkWithStatistic statistic = WorkWithStatistic.getStatistic();
    private NeedMethods need = NeedMethods.getNeed();
    private Locale locale = new Locale("ru", "RU");
    private ResourceBundle res1 = ResourceBundle.getBundle("explain", locale, new UTF8Control());

    public ActionListenerSelectDate(JPanel panel) {
        this.panel = panel;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        List<DataTableSelect> list = new ArrayList<>();
        for(Statistic i :ItemListenerContinueSelect.getMyTable().getData()){
            list.add((DataTableSelect)i);
        }
        if(count == 0){
            count =1;
            //что то делаем со списком
            list.sort(new Comparator<DataTableSelect>() {
                @Override
                public int compare(DataTableSelect o1, DataTableSelect o2) {
                    return o1.getDate().compareTo(o2.getDate());
                }
            });
        }else{
            count =0;
            //что то делаем со списком
            list.sort(new Comparator<DataTableSelect>() {
                @Override
                public int compare(DataTableSelect o1, DataTableSelect o2) {
                    return o2.getDate().compareTo(o1.getDate());
                }
            });
        }
        List<Statistic> list2 = new ArrayList<>();
        list2.addAll(list);
        need.removeJScrollPane(panel, "selectTable");

        MyTable myTable = new MyTable();
        myTable.setColumnName(statistic.getListColumnNameSelect());
        myTable.setData(list2);

        JScrollPane selectScroll = need.createJScrollPane(myTable,"selectTable",25,110,"selectTable",
                10,100,280,270);

        panel.add(selectScroll);
    }
}
