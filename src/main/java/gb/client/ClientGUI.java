package gb.client;

import gb.client.server.ServerWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClientGUI extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private final JTextArea log = new JTextArea();
    private final JPanel panelTop = new JPanel(new GridLayout(2, 1));
    private final JTextField tfIPAdress = new JTextField("127.0.0.1");
    private final JTextField tfMessage = new JTextField();
    private final JButton btnSend = new JButton("Send");
    private ServerWindow serverWindow;
    private String login;

    public ClientGUI(ServerWindow serverWindow, String login) {
        this.serverWindow = serverWindow;
        this.login = login;

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setTitle("Chat Client" + login);
        setLocationRelativeTo(null);

        panelTop.add(tfIPAdress);
        panelTop.add(new JLabel("User logged in as " + login));
        add(panelTop, BorderLayout.NORTH);

        JPanel panelBottom = new JPanel(new BorderLayout());
        panelBottom.add(tfMessage, BorderLayout.CENTER);
        panelBottom.add(btnSend, BorderLayout.EAST);
        add(panelBottom, BorderLayout.SOUTH);

        log.setEditable(false);
        ;
        JScrollPane scrollLog = new JScrollPane(log);
        add(scrollLog, BorderLayout.CENTER);

        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        tfMessage.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendMessage();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        loadChatHistory();
        sendWelcomeMessage();
        setVisible(true);
    }

    private void sendMessage() {
        String message = tfMessage.getText();
        if (!message.isEmpty()) {
            log.append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm")) + " " + login + ": " + message + "\n");
            serverWindow.receiveMessage(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm")) + " " + login + ": " + message);
            saveChatHistory(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm")) + " " + login + ": " + message);
            tfMessage.setText("");
        }
    }

    private void saveChatHistory(String message) {
        serverWindow.saveChatHistory();
    }

    private void loadChatHistory() {
        serverWindow.loadChatHistory();
    }

    private void sendWelcomeMessage() {
        String welcomeMessage = serverWindow.getWelcomeMessage(login);
        log.append(welcomeMessage);
        serverWindow.receiveMessage(welcomeMessage);
    }
}
