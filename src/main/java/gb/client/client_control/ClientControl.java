package gb.client.client_control;

import gb.server.server_ui.ServerGUI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClientControl implements ClientController{
    private ServerGUI serverGUI;
    private String login;

    public ClientControl(ServerGUI serverGUI) {
        this.serverGUI = serverGUI;
    }

    @Override
    public void sendMessage(String message) {
        String msg = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm")) + " " + login + ": " + message;
        serverGUI.receiveMessage(msg);

    }

    @Override
    public void sendWelcomeMessage() {
        String welcomeMessage = serverGUI.getWelcomeMessage(login);
    }

    @Override
    public void loadChatHistory() {
        serverGUI.getChatHistory();
    }

    @Override
    public void setLogin(String login) {
        this.login = login;
    }
}
