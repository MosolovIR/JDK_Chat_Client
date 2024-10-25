package gb.client.server;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ServerWindow extends JFrame {
    private static final int POS_X = 500;
    private static final int POS_Y = 100;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private final JTextArea log = new JTextArea();
    private List<String> chatHistory = new ArrayList<>();

    public ServerWindow() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat Server");
        setAlwaysOnTop(true);

        log.setEditable(false);
        JScrollPane scrollLog = new JScrollPane(log);
        add(scrollLog, BorderLayout.CENTER);

        loadChatHistory();
        setVisible(true);
    }

    public void receiveMessage(String message) {
        log.append(message + "\n");
        chatHistory.add(message);
        saveChatHistory();
    }

    public String getWelcomeMessage(String login) {
        return login + " присоединился к беседе! \n";
    }

    public void serverStartMessage() {
        log.append("Server launched successfully! \n");
    }

    public void serverStopMessage() {
        log.append("Server stopped! \n");
    }

    public List<String> getChatHistory() {
        return chatHistory;
    }

    public void loadChatHistory() {
        try (BufferedReader reader = new BufferedReader(new FileReader("chat_history.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                chatHistory.add(line);
                log.append(line + "\n");
            }
        } catch (IOException e) {
            log.append("Chat history is empty! \n");
        }
    }

    public void saveChatHistory() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("chat_history.txt"))) {
            for (String message: chatHistory) {
                writer.write(message + "\n");
            }
        } catch (IOException e) {
            log.append("Chat history save error! \n");
        }
    }
}
