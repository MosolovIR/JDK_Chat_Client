package gb.server.server_ui;

import java.util.List;

public interface ServerGUI {
    void receiveMessage(String message);
    String getWelcomeMessage(String login);
    void serverStartMessage();
    void serverStopMessage();
    List<String> getChatHistory();
    void loadChatHistory();
}
