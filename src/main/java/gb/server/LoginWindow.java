package gb.client.server;

import gb.client.ClientGUI;

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

    public LoginWindow(ServerWindow serverWindow) {
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
                    new ClientGUI(serverWindow, login);
                    dispose();
                } else {
                    System.out.println("Wrond login or password!");
                }
            }
        });

        setVisible(true);
    }
}
