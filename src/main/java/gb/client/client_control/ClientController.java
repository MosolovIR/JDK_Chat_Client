package gb.client.client_control;

public interface ClientController {
    void sendMessage(String messaqe);
    void sendWelcomeMessage();
    void loadChatHistory();
    void setLogin(String login);
}
