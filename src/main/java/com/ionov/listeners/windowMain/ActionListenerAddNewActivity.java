package com.ionov.listeners.windowMain;

import com.ionov.NeedMethods;
import com.ionov.windows.WindowMain;
import com.ionov.workWithDB.WorkWithDB;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActionListenerAddNewActivity implements ActionListener {
    private JPanel content;
    private JTextArea text;
    private String name;
    private NeedMethods need = NeedMethods.getNeed();
    private ResourceBundle res = ResourceBundle.getBundle("paths");
    private WorkWithDB work = WorkWithDB.getWork();
    private JPanel contentPane;
    private  JButton reset;
    private JButton save;
    private  JButton play;
    private JButton pause;
    private JLabel select;
    private JButton arrowUp;
    private JButton arrowDown;
    private JLabel labelActivity;

    public ActionListenerAddNewActivity(
            JButton arrowDown, JButton arrowUp, JLabel select, JPanel contentPane,
            JButton reset, JButton save,
            JButton play, JButton pause, JLabel labelActivity,
            String name, JTextArea text, JPanel content) {
        this.text = text;
        this.content = content;
        this.name= name;
        this.arrowUp = arrowUp;
        this.arrowDown = arrowDown;
        this.labelActivity = labelActivity;
        this.select = select;
        this.contentPane = contentPane;
        this.reset = reset;
        this.save = save;
        this.play = play;
        this.pause = pause;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String tempName = text.getText();
        Pattern p1 = Pattern.compile("^[ ]{0,23}$");
        Matcher m1 = p1.matcher(tempName);
        if(m1.matches()){
            //пустая или из пробелов
            Thread run = new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    text.setText("");
                    text.setForeground(Color.BLACK);
                    text.setFont(new Font("Times New Roman", Font.PLAIN, 18));
                }
            };
            text.setText("Не может быть пустой, состоять из пробелов");
            text.setForeground(Color.RED);
            text.setFont(new Font("Times New Roman", Font.PLAIN, 10));
            run.start();
        }else {
            Pattern p = Pattern.compile(
                    "^[А-Яа-яA-Za-z0-9!@#%ёЁ^\\-/&*()=_+}:?>\"\\[\\]\\{\\}<№;\\.\\,]{1}" + "[А-Яа-яA-Za-z0-9!@#=%ёЁ^\\-/&*()_+}:?>\"\\[\\]\\{\\}<№;\\.\\, ]{0,21}"
                            + "[А-Яа-яA-Za-z0-9!@#%=ёЁ^\\-/&*()_+}:?>\"\\[\\]\\{\\}<№;\\.\\,]{0,1}$");
            Matcher m = p.matcher(tempName);
            if(m.matches() && tempName.charAt(tempName.length() - 1) != ' ') {
                    if(work.isExistActivity(tempName, name)){
                        //активность уже существует
                        Thread run = new Thread() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(1500);
                                } catch (InterruptedException e1) {
                                    e1.printStackTrace();
                                }
                                text.setText("");
                                text.setForeground(Color.BLACK);
                                text.setFont(new Font("Times New Roman", Font.PLAIN, 18));
                            }
                        };
                        text.setText("Действие уже существует");
                        text.setForeground(Color.RED);
                        text.setFont(new Font("Times New Roman", Font.PLAIN, 10));
                        run.start();
                    }else {
                        work.addActivity(text.getText(), name);
                        JLabel button = new JLabel();
                        button.setBorder(BorderFactory.createEtchedBorder());
                        button.setLayout(null);
                        button.setFont(new Font("Arial", Font.PLAIN, 12));
                        button.addMouseListener(new MouseListenerSelect(arrowDown, arrowUp,
                                select, button,
                                contentPane, reset, save, play, pause, labelActivity));
                        button.setText(text.getText());
                        button.setName(text.getText() + "label");
                        JButton delete = need.createButtonWithIcon(res.getString("delete"), 175, 2, 19, 19);
                        delete.setName(text.getText() + "delete");
                        delete.addActionListener(new ActionListenerDelete(name, content, button));
                        button.add(delete);

                        GridBagConstraints c = new GridBagConstraints(0, GridBagConstraints.RELATIVE,
                                GridBagConstraints.REMAINDER, 1, 1.0, 0, GridBagConstraints.NORTH,
                                GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0),0,5);

                        WindowMain.gbl.setConstraints(button, c);

                        content.add(button, 2);
                        text.setText("");
                        content.repaint();
                    }
            }else{
                //нарушены правила ввода
                Thread run = new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2500);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        text.setText("");
                        text.setForeground(Color.BLACK);
                        text.setFont(new Font("Times New Roman", Font.PLAIN, 18));
                    }
                };
                text.setText("Максимальная длина 23 символов.\n Не может начинаться/заканчиваться пробелом");
                text.setForeground(Color.RED);
                text.setFont(new Font("Times New Roman", Font.PLAIN, 10));
                run.start();
            }
        }
    }
}