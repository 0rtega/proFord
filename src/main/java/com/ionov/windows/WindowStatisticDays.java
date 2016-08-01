package com.ionov.windows;

import com.ionov.NeedMethods;
import com.ionov.UTF8Control;
import com.ionov.listeners.windowMainStatistic.ItemListenerMonthsDate;
import com.ionov.listeners.windowStatisticDays.ActionListenerBack;
import com.ionov.listeners.windowStatisticDays.ActionListenerForward;
import com.ionov.workWithStatistic.MyTable;
import com.ionov.workWithStatistic.WorkWithStatistic;
import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class WindowStatisticDays {
    private JFrame window;
    private JComboBox<String> months;
    private JButton button;
    private String name;
    private  JDialog dialog;
    private ResourceBundle res = ResourceBundle.getBundle("paths");
    private NeedMethods need = NeedMethods.getNeed();
    private WorkWithStatistic statistic =WorkWithStatistic.getStatistic();
    private Locale locale = new Locale("ru", "RU");
    private ResourceBundle res1 = ResourceBundle.getBundle("explain", locale, new UTF8Control());

    public WindowStatisticDays(String name, JFrame window, JComboBox<String> months, JButton button) {
        this.window = window;
        this.name = name;
        this.months = months;
        this.button = button;
        showDialog();
    }

    private void showDialog() {
        dialog = new JDialog(window, Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.setTitle("Статистика дня");
        dialog.setBounds(0, 0, 600, 250);
        dialog.setResizable(false);
        dialog.getContentPane().add(createPanel());
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        dialog.repaint();
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setName("WindowStatisticDays");
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        JButton back = need.createButtonWithIcon(res.getString("arrowLeft"), 10,10,25,25);
        back.setToolTipText(res1.getString("back"));
        int number = ItemListenerMonthsDate.getList().indexOf(Integer.parseInt(button.getText()));
        if(number == 0){
            back.setEnabled(false);
        }
        panel.add(back);

        JButton forward = need.createButtonWithIcon(res.getString("arrowRight"), 560,10,25,25);
        forward.setToolTipText(res1.getString("forward"));
        if(number == ItemListenerMonthsDate.getList().size() - 1){
            forward.setEnabled(false);
        }
        panel.add(forward);

        String monthTemp = (String)months.getSelectedItem();
        String dayTemp = button.getText();
        JLabel date = need.createLabel(monthTemp + "-" + dayTemp,
                35, 10,525, 25, 18, 0);
        panel.add(date);

        JLabel title1 = need.createLabel("Общий отчет за день.", 10, 35, 285, 20, 15,2);
        panel.add(title1);

        JLabel title2 = need.createLabel("Подробный отчет за день.", 300, 35, 285,20,15,4);
        panel.add(title2);

        MyTable myTable = new MyTable();
        myTable.setColumnName(statistic.getColumnNameReportCommonDays());
        myTable.setData( statistic.getDataTableCommonReportDay(name, monthTemp, dayTemp));


        JTable commonReport = new JTable(myTable);
        JTableHeader  tableHeader =  commonReport.getTableHeader();
        tableHeader.setPreferredSize(new Dimension(285, 35));
        tableHeader.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        tableHeader.setToolTipText(res1.getString("commonTable"));

        TableColumnModel columnModel =  commonReport.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(25);
        columnModel.getColumn(2).setPreferredWidth(60);

        JScrollPane scrollPane1 = new JScrollPane(commonReport);
        scrollPane1.setName("commonReport");
        scrollPane1.setBounds(10, 60, 285, 155);

        panel.add(scrollPane1);

        MyTable myTable1 = new MyTable();
        myTable1.setColumnName(statistic.getColumnNameDetailReportDays());
        myTable1.setData(statistic.getDataTableDetailReportDay(name, monthTemp,  dayTemp));

        JTable detailReport = new JTable(myTable1);
        detailReport.getTableHeader().setFont(new Font("Times New Roman", Font.PLAIN, 11));
        detailReport.getColumnModel().getColumn(0).setPreferredWidth(25);

        JScrollPane scrollPane2 = new JScrollPane(detailReport);
        scrollPane2.setName("detailReport");
        scrollPane2.setBounds(305, 60, 280, 155);

        panel.add(scrollPane2);

        back.addActionListener(new ActionListenerBack(panel, back, forward, months, date, name));
        forward.addActionListener(new ActionListenerForward(panel, back, forward,months, date, name));
        return panel;
    }
}
