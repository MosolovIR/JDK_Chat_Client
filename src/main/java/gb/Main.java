package gb;

import gb.server.repository.ServerStorage;
import gb.server.server_control.ServerControl;
import gb.server.server_ui.ServerControlWindow;
import gb.server.server_ui.ServerWindow;

public class Main {
    public static void main(String[] args) {
        ServerStorage repository = new ServerStorage();
        ServerWindow serverWindow = new ServerWindow();
        ServerControl serverControl = new ServerControl(serverWindow,repository);

        new ServerControlWindow(serverControl);
    }
}