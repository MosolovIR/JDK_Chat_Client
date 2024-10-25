package gb.server.server_control;

public interface ServerController {
    void startServer();
    void stopServer();
    void receiveMessage(String message);
}
