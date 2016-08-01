package com.ionov.windows;

import com.ionov.NeedMethods;
import com.ionov.listeners.windowCheckIn.*;
import com.ionov.listeners.windowStart.MouseListenersWindowSrart;
import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class WindowCheckIn {

    private JFrame windowStart;
    private  JDialog dialog;
    private ResourceBundle res = ResourceBundle.getBundle("paths");
    private NeedMethods need = NeedMethods.getNeed();

    public WindowCheckIn(JFrame windowStart) {
        this.windowStart = windowStart;
        showDialog();
    }

    private void showDialog() {
        dialog = new JDialog(windowStart, Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.setTitle("Регистрация");
        dialog.setBounds(0, 0, 350, 270);
        dialog.setResizable(false);
        dialog.getContentPane().add(createPanel());
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        dialog.repaint();
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setName("checkInPanel");
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        panel.add(need.createLabel("логин", 15, 30, 100, 20, 13, 4));
        panel.add(need.createStar(115, 30, 10, 10));
        JTextField login = need.createTextField(130, 30, 180, 25);

        panel.add(login);
        JLabel title = need.createLabel("логин будет вашим именем в приложении", 130, 55, 180, 10, 9, 2);
        panel.add(title);

        JLabel warnLabel1 = need.createLabel("<html>использованы недопустимые символы</html>", 130, 55, 180, 10, 9, 2);
        warnLabel1.setForeground(Color.red);
        warnLabel1.setVisible(false);
        warnLabel1.setName("warnLabel1");
        panel.add(warnLabel1);

        JLabel warnLabel2 = need.createLabel("к сожалению, логин " + login.getText() + " уже занят", 130, 55, 180, 10, 9, 2);
        warnLabel2.setForeground(Color.red);
        warnLabel2.setVisible(false);
        panel.add(warnLabel2);

        JLabel iconOkName = need.createIcon(310, 30, 25, 25, res.getString("ok"));
        iconOkName.setVisible(false);
        panel.add(iconOkName);

        JLabel iconCancelName = need.createIcon(310, 30, 25, 25, res.getString("cancel"));
        iconCancelName.setVisible(false);
        panel.add(iconCancelName);

        panel.add(need.createLabel("пароль", 15, 80, 100, 20, 13, 4));
        panel.add(need.createStar(115, 80, 10, 10));

        panel.add(need.createLabel("повтор пароля", 15, 110, 100, 20, 13, 4));
        JLabel warnPassword = need.createLabel(
                "<html><p style=\"line-height:1;\">пароль должен быть не короче 6 сим-<br>волов, может содержать буквы, а также цифры или спецсимволы</p></html>",
                130, 115, 180, 70, 9, 2);
        panel.add(warnPassword);

        JLabel warnPassword1 = need.createLabel("<html>подтверждение пароля не совпадает<br> с введенным паролем.</html>",
                130, 115, 180, 70, 9, 2);
        warnPassword1.setForeground(Color.RED);
        warnPassword1.setVisible(false);
        panel.add(warnPassword1);

        panel.add(need.createStar(115, 110, 10, 10));

        JPasswordField passwordField = need.createPassword(130, 80, 180, 25);
        panel.add(passwordField);

        JPasswordField passwordField1 = need.createPassword(130, 110, 180, 25);
        panel.add(passwordField1);

        JLabel iconOkPassword = need.createIcon(310, 80, 25, 25, res.getString("ok"));
        iconOkPassword.setVisible(false);
        panel.add(iconOkPassword);

        JLabel iconCancelPassword = need.createIcon(310, 80, 25, 25, res.getString("cancel"));
        iconCancelPassword.setVisible(false);
        panel.add(iconCancelPassword);

        JLabel iconOkPassword1 = need.createIcon(310, 110, 25, 25, res.getString("ok"));
        iconOkPassword1.setVisible(false);
        panel.add(iconOkPassword1);

        JLabel iconCancelPassword1 = need.createIcon(310, 110, 25, 25, res.getString("cancel"));
        iconCancelPassword1.setVisible(false);
        panel.add(iconCancelPassword1);

        JLabel image = need.createIcon(20, 30, 50, 50, res.getString("user"));
        panel.add(image);

        JButton cancel = need.createButton("Отмена", 15, 180, 100, 30);
        cancel.addActionListener(new ActionListenerCancel(dialog));
        panel.add(cancel);

        JButton checkIn = need.createButton("Зарегистрироваться", 130, 180, 180, 30);
        checkIn.setEnabled(false);
        checkIn.addActionListener(new ActionListenerCheckIn(windowStart, dialog, login, passwordField));
        panel.add(checkIn);

        panel.add(need.createStar(40, 225, 10, 10));
        panel.add(need.createLabel(" обязательные для заполнения поля", 50, 225, 180, 15, 10, 2));

        passwordField1.getDocument().addDocumentListener(
                new DocumentListenerPassword2(warnPassword1, warnPassword, checkIn, iconOkPassword1, iconCancelPassword1, passwordField, passwordField1));

        login.getDocument().addDocumentListener(
                new DocumentListenerName(checkIn, title, login, iconOkName, iconCancelName, warnLabel1, warnLabel2));

        passwordField.getDocument().addDocumentListener(
                new DocumentListenerPassword1(checkIn, passwordField, iconOkPassword, iconCancelPassword, warnPassword));

        return panel;
    }
}