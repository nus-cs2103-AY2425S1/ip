package chatbot.impl;

import chatbot.impl.task.Task;
import chatbot.interfaces.MessageView;
import chatbot.interfaces.TaskStorageResult;

public class TaskStorageResultImpl implements TaskStorageResult<Command> {

    private final String message;

    public TaskStorageResultImpl(String message) {
        this.message = message == null ? "" : message;
    }

    /**
     * Shows the result of the task storage operation.
     * @param messageView
     */
    public void showResult(MessageView<Command> messageView) {
        if (message != null) {
            messageView.send(message);
            messageView.endMessage();
        }
    }

    /**
     * Checks if the task storage result is equal to another object.
     * @return true if the task storage result is equal to the other object, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof TaskStorageResultImpl other_casted) {
//            System.out.println("MY MusSSAGE:");
//            System.out.println(message);
//            System.out.println("OTHER MESSAGE:");
//            System.out.println(other_casted.message);
            return message.equals(other_casted.message);
        }
        return false;
    }

    /**
     * Returns the string representation of the task storage result.
     * @return The string representation of the task storage result.
     */
    @Override
    public String toString() {
        return message;
    }
}
