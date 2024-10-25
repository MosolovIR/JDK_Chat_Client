package gb.server.server_ui;

import gb.server.server_control.ServerControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerControlWindow extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 100;
    private final JButton btnStart = new JButton("Start");
    private final JButton btnStop = new JButton("Stop");
    private ServerControl serverControl;

    public ServerControlWindow(ServerControl serverControl) {
        this.serverControl = serverControl;

        serverControlWindowSettings();
        btnSettings();

        setVisible(true);
    }

    private void serverControlWindowSettings() {
        setTitle("Server control window");
        setSize(WIDTH,HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1,2));
    }

    private void btnSettings() {
        btnStop.setEnabled(false);

        add(btnStart);
        add(btnStop);

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                serverControl.startServer();
                btnStart.setEnabled(false);
                btnStop.setEnabled(true);
                new LoginWindow(serverControl.getServerGUI());
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                serverControl.stopServer();
                btnStart.setEnabled(true);
                btnStop.setEnabled(false);
            }
        });
    }
}
