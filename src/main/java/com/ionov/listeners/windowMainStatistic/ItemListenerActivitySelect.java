package com.ionov.listeners.windowMainStatistic;

import com.ionov.NeedMethods;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by user on 01.08.2016.
 */
public class ItemListenerActivitySelect implements ItemListener {
    private JLabel con1;
    private JPanel panel;
    private JComboBox<String> continue1;
    private JButton selectDate;
    private JButton selectTime;
    private JLabel selectTitle;
    private NeedMethods need = NeedMethods.getNeed();

    public ItemListenerActivitySelect(JComboBox<String> continue1, JPanel panel, JLabel con1,
                                      JButton selectDate, JButton selectTime, JLabel selectTitle) {
        this.con1 = con1;
        this.selectDate = selectDate;
        this.selectTime= selectTime;
        this.selectTitle =selectTitle;
        this.continue1 = continue1;
        this.panel = panel;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        con1.setVisible(true);
       need.removeJScrollPane(panel, "selectTable");
        continue1.setSelectedItem(null);
        selectTitle.setVisible(false);
        selectTime.setVisible(false);
        selectDate.setVisible(false);
    }
}
