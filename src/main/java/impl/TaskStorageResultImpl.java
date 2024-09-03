package main.java.impl;

import main.java.interfaces.MessageView;
import main.java.interfaces.TaskStorageResult;

public class TaskStorageResultImpl implements TaskStorageResult<Command> {

    private final String message;

    public TaskStorageResultImpl(String message) {
        this.message = message;
    }

    public void showResult(MessageView<Command> messageView) {
        if (message != null) {
            messageView.send(message);
        }
    }
}
