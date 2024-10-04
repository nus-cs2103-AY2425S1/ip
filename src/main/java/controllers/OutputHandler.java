package controllers;

import javafx.scene.control.ListView;
import models.Message;

public class OutputHandler {
    private ListView<Message> chatListView;
    private boolean isGuiMode;

    // Constructor for GUI mode (with ListView<Message>)
    public OutputHandler(ListView<Message> chatListView) {
        this.chatListView = chatListView;
        this.isGuiMode = true;
    }

    // Constructor for CLI mode (without ListView)
    public OutputHandler() {
        this.chatListView = null;
        this.isGuiMode = false;  // CLI mode
    }

    // Output method for both GUI and CLI
    public void print(String message) {
        assert message != null;
        if (isGuiMode) {
            // If ListView exists, append to it (GUI mode)
            chatListView.getItems().add(new Message(message, false));  // False for bot message
        } else {
            // Otherwise, print to console (CLI mode)
            System.out.println(message);
        }
    }
}
