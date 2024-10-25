package gb.server.server_control;

import gb.server.repository.Repository;
import gb.server.server_ui.ServerGUI;

public class ServerControl implements ServerController{
    private ServerGUI serverGUI;
    private Repository repository;

    public ServerControl (ServerGUI serverGUI, Repository repository) {
        this.serverGUI = serverGUI;
        this.repository = repository;
    }

    @Override
    public void startServer() {
        serverGUI.serverStartMessage();
        serverGUI.getChatHistory();
    }

    @Override
    public void stopServer() {
        serverGUI.serverStopMessage();
    }

    @Override
    public void receiveMessage(String message) {
        serverGUI.receiveMessage(message);
        repository.saveChatHistory(serverGUI.getChatHistory());
    }

    public ServerGUI getServerGUI() {
        return serverGUI;
    }
}
