package chatbot.impl;

import chatbot.impl.task.Task;
import chatbot.interfaces.MessageView;
import chatbot.interfaces.TaskStorageResult;

public class TaskStorageResultImpl implements TaskStorageResult<Command> {

    private final String message;

    public TaskStorageResultImpl(String message) {
        this.message = message;
    }

    public void showResult(MessageView<Command> messageView) {
        if (message != null) {
            messageView.send(message);
            messageView.endMessage();
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof TaskStorageResultImpl other_casted) {
            System.out.println("MY MESSAGE:");
            System.out.println(message);
            System.out.println("OTHER MESSAGE:");
            System.out.println(other_casted.message);
            return message.equals(other_casted.message);
        }
        return false;
    }
}
