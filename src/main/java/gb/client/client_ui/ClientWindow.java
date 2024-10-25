package gb.client.client_ui;

import gb.client.client_control.ClientController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ClientWindow extends JFrame implements ClientGUI {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private final JTextArea log = new JTextArea();
    private final JPanel panelTop = new JPanel(new GridLayout(2, 1));
    private final JTextField tfIPAdress = new JTextField("127.0.0.1");
    private final JTextField tfMessage = new JTextField();
    private final JButton btnSend = new JButton("Send");
    private ClientController clientController;

    public ClientWindow(ClientController clientController, String login) {
        this.clientController = clientController;

        clientUISettings(login);
        panelSettings(login);
        btnSettings(clientController);

        clientController.loadChatHistory();
        clientController.sendWelcomeMessage();

        setVisible(true);
    }

    private void btnSettings(ClientController clientController) {
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientController.sendMessage(tfMessage.getText());
                tfMessage.setText("");
            }
        });

        tfMessage.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    clientController.sendMessage(tfMessage.getText());
                    tfMessage.setText("");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

    private void panelSettings(String login) {
        panelTop.add(tfIPAdress);
        panelTop.add(new JLabel("User logged in as " + login));
        add(panelTop, BorderLayout.NORTH);

        JPanel panelBottom = new JPanel(new BorderLayout());
        panelBottom.add(tfMessage, BorderLayout.CENTER);
        panelBottom.add(btnSend, BorderLayout.EAST);
        add(panelBottom, BorderLayout.SOUTH);

        log.setEditable(false);

        JScrollPane scrollLog = new JScrollPane(log);
        add(scrollLog, BorderLayout.CENTER);
    }

    private void clientUISettings(String login) {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setTitle("Chat Client" + login);
        setLocationRelativeTo(null);
    }

    @Override
    public void showMessage(String message) {
        log.append(message + "\n");
    }
}