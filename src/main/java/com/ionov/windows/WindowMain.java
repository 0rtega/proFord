package com.ionov.windows;

import com.ionov.NeedMethods;
import com.ionov.RoundButton;
import com.ionov.UTF8Control;
import com.ionov.listeners.windowClosed.WindowListenerWindowClosed;
import com.ionov.listeners.windowMain.*;
import com.ionov.listeners.windowStartWithAuto.ActionListenerCheckIn;
import com.ionov.workWithDB.WorkWithDB;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

public class WindowMain {

    private ResourceBundle res = ResourceBundle.getBundle("paths");
    private JFrame windowMain;
    private static HashMap<Component, Boolean> isVisible = new HashMap<>();
    private NeedMethods need = NeedMethods.getNeed();
    private WorkWithDB work = WorkWithDB.getWork();
    private  SystemTray tray;
    private TrayIcon trayIcon;
    public static  GridBagLayout gbl;
    private Locale locale = new Locale("ru", "RU");
    private ResourceBundle res1 = ResourceBundle.getBundle("explain", locale, new UTF8Control());


    public WindowMain(String name) {
        showWindow(name);
        createTray();
    }


    public static void addHashMap(Component string, boolean is){
        isVisible.put(string, is);
    }

    public static void cleanHashMap(){
        isVisible.clear();
    }

    public static HashMap<Component, Boolean> getIsVisible() {
        return isVisible;
    }

    private void createTray(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }
        if (SystemTray.isSupported()) {
            tray = SystemTray.getSystemTray();
            Image image = Toolkit.getDefaultToolkit().getImage(res.getString("icon"));
            trayIcon = new TrayIcon(image, "ProFord");
            trayIcon.addActionListener(new ActionListenerTrayIcon(windowMain));
            trayIcon.setImageAutoSize(true);
        }
        windowMain.addWindowStateListener(new WidowStateListener(windowMain, tray, trayIcon));
        windowMain.setIconImage(Toolkit.getDefaultToolkit().getImage(res.getString("icon")));
    }

    private void showWindow(String name) {
        windowMain = new JFrame("ProFord");
        windowMain.setName("proford");
        windowMain.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        windowMain.setVisible(true);
        windowMain.setResizable(false);
        windowMain.setSize(300, 313);
        windowMain.setLocationRelativeTo(null);
        windowMain.add(createPanel(name));
        windowMain.repaint();
    }

    private JLayeredPane createPanel(String name) {
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setName("layeredPane");
        layeredPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0,0,300,313);
        panel.setLayout(null);
        panel.setName("123");
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        JButton checkIn = need.createButtonWithIcon(res.getString("user2"), 0, 0, 50, 50);
        checkIn.addActionListener(new ActionListenerCheckIn(windowMain));
        checkIn.setEnabled(false);
        panel.add(checkIn);
        JButton cancel = need.createButtonWithIcon(res.getString("close"), 244, 0,50,50 );
        cancel.addActionListener(new ActionListenerCancelUser(windowMain));
        cancel.setToolTipText(res1.getString("comeOut"));
        panel.add(cancel);
        JButton statistic = need.createButton(name, 49,0, 196, 50);
        statistic.addMouseListener(new MouseListenerStatistic(statistic));
        statistic.addActionListener(new ActionListenerStatistic(name, windowMain));
        statistic.setFont(new Font("Arial", Font.BOLD, 30));
        statistic.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        statistic.setToolTipText(res1.getString("statistic"));
        panel.add(statistic);
        JButton unlock = need.createButtonWithIcon(res.getString("unlock"), 95,90,100,100);
        unlock.addActionListener(new ActionListenerUnlock(windowMain, name));
        unlock.setVisible(false);
        unlock.setName("unlock");
        unlock.setToolTipText(res1.getString("unlock"));
        panel.add(unlock);
        JButton lock = need.createButtonWithIcon(res.getString("lock"), 0, 110,25,25 );
        lock.setToolTipText(res1.getString("lock"));
        lock.addActionListener(new ActionListenerLock(windowMain));

        JLabel timer = need.createLabel("00:00:00",0, 150, 294, 135, 55, 0);
        timer.setName("timer");
        timer.setForeground(Color.BLACK);
        timer.setFont(new Font("Arial", Font.PLAIN, 70));
        timer.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        timer.setHorizontalAlignment(0);
        timer.setVerticalAlignment(0);
        timer.setBackground(Color.WHITE);
        timer.setLayout(null);

        timer.add(lock);
        JButton alarm = need.createButtonWithIcon(res.getString("alarm1"), 0,0,25,25);
        alarm.setToolTipText(res1.getString("alarm"));
        alarm.addActionListener(new ActionListenerAlarm(windowMain));
        alarm.setDisabledIcon(new ImageIcon(res.getString("alarm2")));
        timer.add(alarm);

        JButton alarm1 = need.createButton("00:00:00",220,0,74 ,25);
        alarm1.setName("alarm1");
        alarm1.setToolTipText(res1.getString("alarm1"));
        alarm1.addActionListener(new ActionListenerAlarm1(timer, windowMain, alarm1));
        timer.add(alarm1);

        JLabel labelActivity = need.createLabel("-", 35, 0, 175, 25,11,0);
        labelActivity.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        labelActivity.setToolTipText(res1.getString("activity"));
        timer.add(labelActivity);

        panel.add(timer);

        JButton save = new RoundButton(new ImageIcon(res.getString("save2")), res.getString("save3"), res.getString("save1"));
        save.setName("save");
        save.setBounds(110, 65, 70,70);
        save.setToolTipText(res1.getString("save"));
        save.setDisabledIcon(new ImageIcon(res.getString("save4")));
        save.setVisible(false);

        JButton play = new RoundButton(new ImageIcon(res.getString("play2")), res.getString("play3"), res.getString("play1"));
        play.setBounds(200, 60, 80,80);
        play.setName("play");
        play.setToolTipText(res1.getString("play"));
        play.setDisabledIcon(new ImageIcon(res.getString("play4")));
        play.setVisible(false);

        JButton pause = new RoundButton(new ImageIcon(res.getString("pause2")), res.getString("pause3"), res.getString("pause1"));
        pause.setBounds(212, 72, 56,56);
        pause.setName("pause");
        pause.setToolTipText(res1.getString("pause"));
        pause.setDisabledIcon(new ImageIcon(res.getString("pause4")));
        pause.setVisible(false);

        JButton reset = new RoundButton(new ImageIcon(res.getString("reset2")), res.getString("reset3"), res.getString("reset1"));
        reset.setBounds(20, 65, 70,70);
        reset.setName("reset");
        reset.setToolTipText(res1.getString("reset"));
        reset.setDisabledIcon(new ImageIcon(res.getString("reset4")));
        reset.setVisible(false);

        play.addActionListener(new ActionListenerPlay(windowMain, alarm, save, alarm1, timer, pause, play));
        pause.addActionListener(new ActionListenerPause(reset, alarm, save, play, pause));
        panel.add(reset);
        panel.add(pause);
        panel.add(save);
        panel.add(play);

        layeredPane.add(panel, JLayeredPane.DEFAULT_LAYER);

        JLabel select = new JLabel();
        select.setName("select");
        select.setBounds(40,80,220,30);
        select.setLayout(null);

        reset.addActionListener(new ActionListenerReset(labelActivity, reset, select,
                alarm, timer, play, pause, save, alarm1));
        save.addActionListener(new ActionListenerSave( windowMain, name,labelActivity, reset, select,
                alarm1, timer, play, pause, save, alarm ));

        JLabel titleActivity = new JLabel("Выберете действие или добавьте свое.");
        titleActivity.setFont(new Font("Times New Roman", Font.PLAIN, 11));
        titleActivity.setName("titleActivity");
        titleActivity.setForeground(Color.BLACK);
        titleActivity.setBounds(0,0,190,30);
        titleActivity.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        titleActivity.setBackground(Color.WHITE);

        select.add(titleActivity);

        JButton arrowDown = need.createButtonWithIcon(res.getString("arrowDown"), 189,0,30,30);
        select.add(arrowDown);

        JButton arrowUp = need.createButtonWithIcon(res.getString("arrowUp"), 189,0,30,30);
        select.add(arrowUp);
        arrowUp.setVisible(false);

        panel.add(select);

        JPanel contentPane = new JPanel();
        contentPane.setName("contentPane");
        contentPane.setBounds(40,109,219,150);
        contentPane.setLayout(new BorderLayout());
        contentPane.setVisible(false);

        arrowDown.addActionListener(new ActionListenerArrow(contentPane, arrowUp, arrowDown));
        arrowUp.addActionListener(new ActionListenerArrow1( arrowDown, arrowUp, contentPane));

        JPanel contactListPanel = new JPanel();
        contactListPanel.setName("contactListPanel");
        contactListPanel.setBackground(Color.WHITE);
         gbl = new GridBagLayout();
        contactListPanel.setLayout(gbl);
        contactListPanel.setSize(180, 50);


        GridBagConstraints c = new GridBagConstraints(0, GridBagConstraints.RELATIVE,
                GridBagConstraints.REMAINDER, 1, 1.0, 0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0),0,5);


        JButton addNewActivity = new JButton("Добавить действие:");
        addNewActivity.setName("addNewActivity");
        addNewActivity.addMouseListener(new MouseListenerButton(addNewActivity));
        addNewActivity.setBorder(BorderFactory.createEtchedBorder());
        addNewActivity.setBackground(Color.WHITE);
        addNewActivity.setForeground(Color.BLACK);
        addNewActivity.setFont(new Font("Arial", Font.PLAIN, 12));
        addNewActivity.setFocusPainted(false);

        JTextArea newActivity = new JTextArea();
        newActivity.setBorder(BorderFactory.createEtchedBorder());
        newActivity.setName("newActivity");
        newActivity.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        newActivity.requestFocus();

        gbl.setConstraints(newActivity, c);
        contactListPanel.add(newActivity);
        gbl.setConstraints(addNewActivity, c);
        contactListPanel.add(addNewActivity);

        for (String i : work.getActivityByName(name))
        {
            JLabel button = new JLabel();
            button.setBorder(BorderFactory.createEtchedBorder());
            button.setLayout(null);
            button.setFont(new Font("Arial", Font.PLAIN, 12));
            button.addMouseListener(new MouseListenerSelect(arrowDown, arrowUp,
                    select, button,
                    contentPane,reset, save, play, pause, labelActivity));
            button.setText(i);
            button.setName(i+ "statistic");
            JButton delete = need.createButtonWithIcon(res.getString("delete"), 175, 2, 19,19);
            delete.setToolTipText(res1.getString("delete"));
            delete.setName(i + "delete");
            delete.addActionListener(new ActionListenerDelete(name, contactListPanel, button));
            button.add(delete);
            gbl.setConstraints(button, c);
            contactListPanel.add(button);
        }

        addNewActivity.addActionListener(new ActionListenerAddNewActivity(
                arrowDown, arrowUp,
                select,
                contentPane,reset, save, play, pause, labelActivity,
                name, newActivity, contactListPanel));

        JScrollPane scrollPane = new JScrollPane(contactListPanel);
        scrollPane.setName("scrollPane");
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        contentPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        contentPane.add(scrollPane, BorderLayout.CENTER);

        layeredPane.add(contentPane, JLayeredPane.POPUP_LAYER);

        windowMain.addWindowListener(new WindowListenerWindowClosed(timer, name, labelActivity, windowMain));

        return layeredPane;
    }

    public static void main(String[] args) {
        new WindowMain("Paul");
    }
}
