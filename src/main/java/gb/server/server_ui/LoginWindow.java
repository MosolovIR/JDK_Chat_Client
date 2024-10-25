package gb.server.server_ui;

import gb.client.client_control.ClientControl;
import gb.client.client_control.ClientController;
import gb.client.client_ui.ClientWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginWindow extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 150;
    private final JTextField tfLogin = new JTextField();
    private final JPasswordField tfPassword = new JPasswordField();
    private final JButton btnStart = new JButton("Start");
    private ServerGUI serverGUI;

    public LoginWindow(ServerGUI serverGUI) {
        this.serverGUI = serverGUI;

        loginWindowSettings();
        setVisible(true);
    }

    private void loginWindowSettings() {
        setTitle("Login");
        setSize(WIDTH,HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(3,2));

        add(new JLabel("Login: "));
        add(tfLogin);
        add(new JLabel("Password: "));
        add(tfPassword);
        add(new JLabel(""));
        add(btnStart);

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = tfLogin.getText();
                char[] password = tfPassword.getPassword();

                if (!login.isEmpty() && password.length > 0) {
                    ClientController clientControl = new ClientControl(serverGUI);
                    clientControl.setLogin(login);
                    ClientWindow clientWindow = new ClientWindow(clientControl, login);
                    dispose();
                } else {
                    System.out.println("Wrong login or password!");
                }
            }
        });
    }
}
