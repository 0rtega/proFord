package com.ionov.listeners.windowMainStatistic;

import com.ionov.NeedMethods;
import com.ionov.workWithStatistic.DataTableSelect;
import com.ionov.workWithStatistic.MyTable;
import com.ionov.workWithStatistic.Statistic;
import com.ionov.workWithStatistic.WorkWithStatistic;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by user on 26.07.2016.
 */
public class ActionListenerSelectTime implements ActionListener {
    private static int count = 0;
    private JPanel panel;
    private WorkWithStatistic statistic = WorkWithStatistic.getStatistic();
   private NeedMethods need = NeedMethods.getNeed();

    public ActionListenerSelectTime(JPanel panel) {
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
            //что то делаем со списко
            list.sort(new Comparator<DataTableSelect>() {
                @Override
                public int compare(DataTableSelect o1, DataTableSelect o2) {
                    return o1.getCommonTime().compareTo(o2.getCommonTime());
                }
            });
        }else{
            count =0;
            //что то делаем со списко
            list.sort(new Comparator<DataTableSelect>() {
                @Override
                public int compare(DataTableSelect o1, DataTableSelect o2) {
                    return o2.getCommonTime().compareTo(o1.getCommonTime());
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
