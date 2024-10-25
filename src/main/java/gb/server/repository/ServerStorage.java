package gb.server.repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ServerStorage implements Repository {
    private static final String FILE_PATH = "chat_history.txt";

    @Override
    public void saveChatHistory(List<String> chatHistory) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (String message : chatHistory) {
                writer.write(message + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> loadChatHistory() {
        List<String> history = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                history.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return history;
    }
}

