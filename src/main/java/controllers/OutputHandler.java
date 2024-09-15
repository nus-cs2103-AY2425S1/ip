package controllers;
import javafx.scene.control.TextArea;

public class OutputHandler {
    private TextArea chatArea;

    // Constructor for GUI mode (with TextArea)
    public OutputHandler(TextArea chatArea) {
        this.chatArea = chatArea;
    }

    // Constructor for CLI mode (without TextArea)
    public OutputHandler() {
        this.chatArea = null;  // No TextArea, console mode
    }

    // Output method for both GUI and CLI
    public void print(String message) {
        assert message != null;
        if (chatArea != null) {
            // If TextArea exists, append to it (GUI)
            chatArea.appendText(message + "\n");
        } else {
            // Otherwise, print to console (CLI)
            System.out.println(message);
        }
    }
}

