package gb.server.repository;

import java.util.List;

public interface Repository {
    void saveChatHistory(List<String> chatHistory);
    List<String> loadChatHistory();
}
