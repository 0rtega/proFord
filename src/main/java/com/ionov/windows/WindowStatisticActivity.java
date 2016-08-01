package com.ionov.windows;

import com.ionov.NeedMethods;
import com.ionov.UTF8Control;
import com.ionov.listeners.windowMainStatistic.ItemListenerMonthsActivity;
import com.ionov.listeners.windowStatisticActivity.ActionListenerBack;
import com.ionov.listeners.windowStatisticActivity.ActionListenerForward;
import com.ionov.workWithStatistic.MyTable;
import com.ionov.workWithStatistic.WorkWithStatistic;
import javax.swing.*;
import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class WindowStatisticActivity {
    private JFrame window;
    private JComboBox<String> months1;
    private JButton button;
    private String name;
    private JComboBox<String> activityCombo;
    private  JDialog dialog;
    private ResourceBundle res = ResourceBundle.getBundle("paths");
    private NeedMethods need = NeedMethods.getNeed();
    private WorkWithStatistic statistic = WorkWithStatistic.getStatistic();
    private Locale locale = new Locale("ru", "RU");
    private ResourceBundle res1 = ResourceBundle.getBundle("explain", locale, new UTF8Control());

    public WindowStatisticActivity(JComboBox<String> activityCombo, String name, JFrame window, JComboBox<String> months1, JButton button) {
        this.window = window;
        this.activityCombo = activityCombo;
        this.name = name;
        this.months1 = months1;
        this.button = button;
        showDialog();
    }

    private void showDialog() {
        dialog = new JDialog(window, Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.setTitle("Статистика действия");
        dialog.setBounds(0, 0, 500, 250);
        dialog.setResizable(false);
        dialog.getContentPane().add(createPanel());
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        dialog.repaint();
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setName("WindowStatisticActivity");
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        JButton back = need.createButtonWithIcon(res.getString("arrowLeft"), 10,10,25,25);
        back.setToolTipText(res1.getString("back"));
        int number = ItemListenerMonthsActivity.getList().indexOf(Integer.parseInt(button.getText()));
        if(number == 0){
            back.setEnabled(false);
        }
        panel.add(back);

        JButton forward = need.createButtonWithIcon(res.getString("arrowRight"), 460,10,25,25);
        forward.setToolTipText(res1.getString("forward"));
        if(number == ItemListenerMonthsActivity.getList().size() - 1){
            forward.setEnabled(false);
        }
        panel.add(forward);

        String monthTemp = (String)months1.getSelectedItem();
        String dayTemp = button.getText();
        String activityTemp = (String)activityCombo.getSelectedItem();

        JLabel date = need.createLabel(monthTemp + "-" + dayTemp,
                35, 10,425, 25, 18, 0);
        panel.add(date);

        JLabel name = need.createLabel("Имя", 10, 70, 90, 30, 13, 0);
        name.setBorder(BorderFactory.createEtchedBorder());
        panel.add(name);

        JLabel name1 = need.createLabel(activityTemp, 100,70,90,30,13, 0);
        name1.setBorder(BorderFactory.createEtchedBorder());
        panel.add(name1);

        JLabel allTime = need.createLabel("ОВН(Д)", 10,100,90,30,11, 0);
        allTime.setBorder(BorderFactory.createEtchedBorder());
        allTime.setToolTipText(res1.getString("activityLabel1"));
        panel.add(allTime);

        JLabel allTime1 = need.createLabel(statistic.getTimeActivityDay(this.name, monthTemp, dayTemp,activityTemp ),
                100,100,90,30,13, 0);
        allTime1.setBorder(BorderFactory.createEtchedBorder());
        panel.add(allTime1);

        JLabel ratioWatchTimeMonth = need.createLabel("ОВН(Д)/ОВ(Д)", 10, 130, 90, 30, 11, 0);
        ratioWatchTimeMonth.setBorder(BorderFactory.createEtchedBorder());
        ratioWatchTimeMonth.setToolTipText(res1.getString("activityLabel1"));
        panel.add(ratioWatchTimeMonth);

        JLabel ratioWatchTimeMonth1 = need.createLabel(statistic.getRatioDayAndCommonWatchTime(this.name, monthTemp , dayTemp, activityTemp),
                100,130,90,30,13, 0);
        ratioWatchTimeMonth1.setBorder(BorderFactory.createEtchedBorder());
        panel.add(ratioWatchTimeMonth1);

        JLabel ratioCommonTimeMonth = need.createLabel("ОВН(Д)/РВ(Д)", 10, 160, 90, 30, 11, 0);
        ratioCommonTimeMonth.setBorder(BorderFactory.createEtchedBorder());
        ratioCommonTimeMonth.setToolTipText(res1.getString("activityLabel1"));
        panel.add(ratioCommonTimeMonth);

        JLabel ratioCommonTimeMonth1 = need.createLabel(statistic.getRatioDayCommonTimeDay(this.name, monthTemp, dayTemp, activityTemp),
                100,160,90, 30,13, 0);
        ratioCommonTimeMonth1.setBorder(BorderFactory.createEtchedBorder());
        panel.add(ratioCommonTimeMonth1);

        JLabel title2 = need.createLabel("Подробный отчет за день.", 205, 35, 285,20,15,4);
        panel.add(title2);

        MyTable myTable = new MyTable();
        myTable.setColumnName(statistic.getColumnNameDetailReportDays());
        myTable.setData(statistic.getDataTableDetailReportDayForOneActivity(this.name, monthTemp , ""+ dayTemp, activityTemp));

        JScrollPane scrollPane2 = need.createJScrollPane(myTable, "detailReport", 25,80,
                "detailReport", 205,60,280,155);

        panel.add(scrollPane2);

        back.addActionListener(new ActionListenerBack(panel, activityCombo, allTime1, ratioWatchTimeMonth1, ratioCommonTimeMonth1,
                back, forward, months1, date,
                this.name));
        forward.addActionListener(new ActionListenerForward( panel, activityCombo, allTime1, ratioWatchTimeMonth1, ratioCommonTimeMonth1,
                back, forward, months1, date, this.name));
        return panel;
    }
}
