package com.ionov.windows;

import com.ionov.NeedMethods;
import com.ionov.UTF8Control;
import com.ionov.listeners.windowMainStatistic.*;
import com.ionov.workWithStatistic.MyTable;
import com.ionov.workWithStatistic.WorkWithStatistic;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class WindowMainStatistic {
    private String name;
    private JFrame window;
    private  JDialog dialog;
    private NeedMethods need = NeedMethods.getNeed();
    private WorkWithStatistic statistic = WorkWithStatistic.getStatistic();
    private ResourceBundle res = ResourceBundle.getBundle("paths");
    private  Locale locale = new Locale("ru", "RU");
    private ResourceBundle res1 = ResourceBundle.getBundle("explain", locale, new UTF8Control());

    public WindowMainStatistic(String name, JFrame window) {
        this.name = name;
        this.window = window;
        showDialog();
    }

    private void showDialog(){
        dialog = new JDialog(window, Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.setTitle("Статистика");
        dialog.setBounds(0, 0, 300, 400);
        dialog.setResizable(false);
        dialog.getContentPane().add(createLayeredPane());
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        dialog.repaint();
    }


    private JLayeredPane createLayeredPane(){
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setName("layeredPane");
        layeredPane.setLayout(null);

        JPanel datePanel = createPanel("datePanel");
        datePanel.setVisible(false);

        JPanel activityPanel = createPanel("activityPanel");
        activityPanel.setVisible(false);

        JPanel selectPanel = createPanel("selectPanel");
        selectPanel.setVisible(false);

        JPanel mainPanel = createPanel("mainPanel");

        //наполнение главной панели

        JLabel name = need.createLabel("Имя:", 10, 10, 170, 30, 11, 2);
        name.setBorder(BorderFactory.createEtchedBorder());
        mainPanel.add(name);

        JLabel name1 = need.createLabel(this.name, 180,10,110,30,12, 0);
        name1.setBorder(BorderFactory.createEtchedBorder());
        mainPanel.add(name1);

        JLabel begin = need.createLabel("Начало наблюдения:", 10, 40, 170, 30, 12,2);
        begin.setBorder(BorderFactory.createEtchedBorder());
        mainPanel.add(begin);

        JLabel begin1 = need.createLabel(statistic.getBeginWatch(this.name), 180, 40, 110, 30,12,0);
        begin1.setBorder(BorderFactory.createEtchedBorder());
        mainPanel.add(begin1);

        JLabel timeWatch = need.createLabel("ОВ(В)", 10, 70, 170, 30, 13,2 );
         timeWatch.setBorder(BorderFactory.createEtchedBorder());

        timeWatch.setToolTipText(res1.getString("ОВ"));
        mainPanel.add(timeWatch);

        JLabel timeWatch1 = need.createLabel(statistic.getTimeWatch(this.name), 180, 70, 110, 30, 13,0);
        timeWatch1.setBorder(BorderFactory.createEtchedBorder());
        mainPanel.add(timeWatch1);

        JLabel ratio = need.createLabel("(ОВ(В)/РВ(В))*100%",
        10, 100, 170, 30, 13,2 );
        ratio.setBorder(BorderFactory.createEtchedBorder());
        ratio.setToolTipText(res1.getString("РВ(В)"));
        mainPanel.add(ratio);

        JLabel ratio1 = need.createLabel(statistic.getRatioBeginWatch(), 180, 100, 110, 30,13,0);
        ratio1.setBorder(BorderFactory.createEtchedBorder());
        mainPanel.add(ratio1);

        JLabel activity = need.createLabel("Общие данные за все время:", 10, 130, 250, 30, 12,2);
        mainPanel.add(activity);

        MyTable myTable1 = new MyTable();
        myTable1.setColumnName(statistic.getColumnNameMainWindow());
        myTable1.setData(statistic.getDataTableMainWindow(this.name));

        JTable table = new JTable(myTable1);
        JTableHeader tableHeader =  table.getTableHeader();
        tableHeader.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        tableHeader.setToolTipText(res1.getString("tableMain"));
        tableHeader.setPreferredSize(new Dimension(285, 35));
        table.getColumnModel().getColumn(0).setPreferredWidth(25);
        table.getColumnModel().getColumn(2).setPreferredWidth(60);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 160, 280, 140);
        mainPanel.add(scrollPane);

        JLabel report = need.createLabel("Отчеты", 10, 310, 280, 18,15,0 );
        report.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        mainPanel.add(report);

        JButton date = need.createButton1("По дате", 10, 330, 85, 40);
        date.addActionListener(new ActionListenerReports(datePanel));
        mainPanel.add(date);

        JButton action = need.createButton1("По действию",100, 330, 100, 40);
        action.addActionListener(new ActionListenerReports(activityPanel));
        mainPanel.add(action);

        JButton sample = need.createButton1("Выборки", 205, 330, 85, 40);
        sample.addActionListener(new ActionListenerReports(selectPanel));
        mainPanel.add(sample);

        layeredPane.add(mainPanel, JLayeredPane.DEFAULT_LAYER);
/////////////////////////////////

        //наполнение панели для отчета по дате

        JButton backDate = need.createButtonWithIcon(res.getString("arrowLeft"), 10,10,25,25);
        backDate.setToolTipText(res1.getString("bottom"));
        backDate.addActionListener(new ActionListenerBack(datePanel));
        datePanel.add(backDate);

        JLabel label = need.createLabel("Выберете месяц:", 40,10,110,25,12,0);
        datePanel.add(label);

        ArrayList<String> listMonths = statistic.getListMonthsByName(this.name);
        JComboBox<String> months= new JComboBox<>(listMonths.toArray(new String[0]));
        months.setBounds(150, 10, 133, 25);
        months.setBackground(Color.WHITE);
        months.setSelectedItem(null);

        datePanel.add(months);

        datePanel.add(need.createLabel("ПН", 10,50, 38,25,12,0,1 ));
        datePanel.add(need.createLabel("ВТ", 49,50, 38,25,12,0,1 ));
        datePanel.add(need.createLabel("СР", 88,50, 38,25,12,0,1 ));
        datePanel.add(need.createLabel("ЧТ", 127,50, 38,25,12,0,1 ));
        datePanel.add(need.createLabel("ПТ", 166,50, 38,25,12,0,1 ));
        datePanel.add(need.createLabel("СБ", 205,50, 38,25,12,0,1 ));
        datePanel.add(need.createLabel("ВС", 245,50, 38,25,12,0,1 ));

        JLabel container = new JLabel();
        container.setVisible(false);
        container.setBounds(10, 80, 273, 140);
        container.setLayout(new GridLayout(6, 7));
        for(int i = 0; i <42; i++ ){
            JButton button = need.createButton1(i+"", 0 ,0 ,0 ,0);
            button.setBorder(null);
            button.setVisible(false);
            button.addActionListener(new ActionListenerButtonDate(this.name, window, months, button));
            container.add(button);
        }

        JLabel title = need.createLabel("Общие данные за месяц:", 10, 225, 200, 20, 13,2);
        title.setVisible(false);
        datePanel.add(title);

        months.addItemListener(new ItemListenerMonthsDate( datePanel, this.name,months ,title, container));

        datePanel.add(container);


        layeredPane.add(datePanel, JLayeredPane.POPUP_LAYER);
//////////////////////////////////

        //наполнение панели для отчета по активностям

        JLabel con = new JLabel();
        con.setLayout(null);
        con.setBounds(40,40,280,40);
        con.setVisible(false);

        activityPanel.add(con);

        JButton backDate1 = need.createButtonWithIcon(res.getString("arrowLeft"), 10,10,25,25);
        backDate1.setToolTipText(res1.getString("bottom"));
        backDate1.addActionListener(new ActionListenerBack(activityPanel));
        activityPanel.add(backDate1);

        JLabel activity1 = need.createLabel("Выберете действие:", 40,10,140,25,11,2);

        activityPanel.add(activity1);

        ArrayList<String> listActivity = statistic.getListActivity(this.name);
        JComboBox<String> activityCombo= new JComboBox<>(listActivity.toArray(new String[0]));
        activityCombo.setBounds(150, 10, 133, 25);
        activityCombo.setBackground(Color.WHITE);
        activityCombo.setSelectedItem(null);

        activityPanel.add(activityCombo);


        JLabel label1 = need.createLabel("Выберете месяц:", 0,0,120,25,12,2);
        con.add(label1);

        JComboBox<String> months1= new JComboBox<>();
        months1.setBounds(110, 0, 133, 25);
        months1.setBackground(Color.WHITE);
        months1.setSelectedItem(null);

        con.add(months1);

        activityPanel.add(need.createLabel("ПН", 10,80, 38,25,12,0,1 ));
        activityPanel.add(need.createLabel("ВТ", 49,80, 38,25,12,0,1 ));
        activityPanel.add(need.createLabel("СР", 88,80, 38,25,12,0,1 ));
        activityPanel.add(need.createLabel("ЧТ", 127,80, 38,25,12,0,1 ));
        activityPanel.add(need.createLabel("ПТ", 166,80, 38,25,12,0,1 ));
        activityPanel.add(need.createLabel("СБ", 205,80, 38,25,12,0,1 ));
        activityPanel.add(need.createLabel("ВС", 245,80, 38,25,12,0,1 ));

        JLabel container1 = new JLabel();
        container1.setVisible(false);
        container1.setBounds(10, 110, 273, 160);
        container1.setLayout(new GridLayout(6, 7));
        for(int i = 0; i <42; i++ ){
            JButton button = need.createButton1(i+"", 0 ,0 ,0 ,0);
            button.setBorder(null);
            button.setVisible(false);
            button.addActionListener(new ActionListenerButtonActivity(activityCombo, this.name,  months1, button, window));
            container1.add(button);
        }

        JLabel allTime = need.createLabel("ОВН(М)", 10, 280, 170, 30, 13, 2);
        allTime.setBorder(BorderFactory.createEtchedBorder());
        allTime.setToolTipText(res1.getString("activityLabel"));
        allTime.setVisible(false);
        activityPanel.add(allTime);

        JLabel allTime1 = need.createLabel("", 180,280,103,30,13, 0);
        allTime1.setBorder(BorderFactory.createEtchedBorder());
        allTime1.setVisible(false);
        activityPanel.add(allTime1);

        JLabel ratioWatchTimeMonth = need.createLabel("ОВН(М)/ОВ(М)", 10, 310, 170, 30, 13, 2);
        ratioWatchTimeMonth.setBorder(BorderFactory.createEtchedBorder());
        ratioWatchTimeMonth.setToolTipText(res1.getString("activityLabel"));
        ratioWatchTimeMonth.setVisible(false);
        activityPanel.add(ratioWatchTimeMonth);

        JLabel ratioWatchTimeMonth1 = need.createLabel("", 180,310,103,30,13, 0);
        ratioWatchTimeMonth1.setBorder(BorderFactory.createEtchedBorder());
        ratioWatchTimeMonth1.setVisible(false);
        activityPanel.add(ratioWatchTimeMonth1);

        JLabel ratioCommonTimeMonth = need.createLabel("ОВН(М)/РВ(М)", 10, 340, 170, 30, 13, 2);
        ratioCommonTimeMonth.setBorder(BorderFactory.createEtchedBorder());
        ratioCommonTimeMonth.setToolTipText(res1.getString("activityLabel"));
        ratioCommonTimeMonth.setVisible(false);
        activityPanel.add(ratioCommonTimeMonth);

        JLabel ratioCommonTimeMonth1 = need.createLabel("", 180,340,103,30,13, 0);
        ratioCommonTimeMonth1.setBorder(BorderFactory.createEtchedBorder());
        ratioCommonTimeMonth1.setVisible(false);
        activityPanel.add(ratioCommonTimeMonth1);


        months1.addItemListener(new ItemListenerMonthsActivity(activityCombo, this.name, months1, allTime,allTime1,
                ratioWatchTimeMonth, ratioWatchTimeMonth1,
                ratioCommonTimeMonth, ratioCommonTimeMonth1, container1));
        activityCombo.addItemListener(new ItemListenerActivityCombo(this.name, activityCombo, allTime,allTime1,
                ratioWatchTimeMonth, ratioWatchTimeMonth1,
                ratioCommonTimeMonth, ratioCommonTimeMonth1,container1, con, months1));
        activityPanel.add(container1);

        layeredPane.add(activityPanel, JLayeredPane.POPUP_LAYER);
///////////////////////////////////

        //панель для отчетов выборки
        JButton backSelect = need.createButtonWithIcon(res.getString("arrowLeft"), 10,10,25,25);
        backSelect.setToolTipText(res1.getString("bottom"));
        backSelect.addActionListener(new ActionListenerBack(selectPanel));
        selectPanel.add(backSelect);

        JLabel select1 = need.createLabel("Выберете действие:", 40,10,140,25,11,2);

        selectPanel.add(select1);

        ArrayList<String> listActivity1 = statistic.getListActivity(this.name);
        JComboBox<String> activityCombo1= new JComboBox<>(listActivity1.toArray(new String[0]));
        activityCombo1.setBounds(150, 10, 133, 25);
        activityCombo1.setBackground(Color.WHITE);
        activityCombo1.setSelectedItem(null);

        selectPanel.add(activityCombo1);


        JLabel con1 = new JLabel();
        con1.setLayout(null);
        con1.setBounds(40,40,280,40);
        con1.setVisible(false);

        JLabel select2 = need.createLabel("Выберете время:", 0,0,120,25,12,2);
        con1.add(select2);

        ArrayList<String> list = new ArrayList<>();
        list.add("По дням");
        list.add("По месяцам");
        JComboBox<String> continue1= new JComboBox<>(list.toArray(new String [0]));
        continue1.setBounds(110, 0, 133, 25);
        continue1.setBackground(Color.WHITE);
        continue1.setSelectedItem(null);

        con1.add(continue1);

        selectPanel.add(con1);
        JLabel selectTitle = need.createLabel("Сортировать по:", 10, 70, 100, 20, 12,2);
        selectTitle.setVisible(false);
        selectPanel.add(selectTitle);

        JButton selectDate = need.createButton1("по дате", 110, 70, 70, 20);
        selectDate.setVisible(false);
        selectPanel.add(selectDate);

        JButton selectTime = need.createButton1("по времени", 190, 70, 100, 20);
        selectTime.setVisible(false);
        selectPanel.add(selectTime);

        selectDate.addActionListener(new ActionListenerSelectDate( selectPanel));
        selectTime.addActionListener(new ActionListenerSelectTime(selectPanel));

        activityCombo1.addItemListener(new ItemListenerActivitySelect(continue1, selectPanel, con1,
                selectDate,selectTime, selectTitle));
        continue1.addItemListener(new ItemListenerContinueSelect(activityCombo1, this.name, selectPanel, selectDate, selectTime, selectTitle));


        layeredPane.add(selectPanel, JLayeredPane.POPUP_LAYER);

        return layeredPane;
    }

    private JPanel createPanel(String name){
        JPanel panel = new JPanel();
        panel.setBounds(0,0,300,400);
        panel.setLayout(null);
        panel.setName(name);
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        return panel;
    }
}
