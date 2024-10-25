package gb.server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerControlWindow extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 100;
    private final JButton btnStart = new JButton("Start");
    private final JButton btnStop = new JButton("Stop");
    private ServerWindow serverWindow;


    public ServerControlWindow() {
        setTitle("Server control window");
        setSize(WIDTH,HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1,2));

        btnStop.setEnabled(false);

        add(btnStart);
        add(btnStop);

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                serverWindow = new ServerWindow();
                serverWindow.serverStartMessage();
                new gb.server.LoginWindow(serverWindow);

                btnStart.setEnabled(false);
                btnStop.setEnabled(true);

            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (serverWindow != null) {
                    serverWindow.dispose();
                    serverWindow.serverStopMessage();
                }

                btnStart.setEnabled(true);
                btnStop.setEnabled(false);

            }
        });

        setVisible(true);
    }
}
