package chatgpt.task;

import chatgpt.exception.ChatBotException;

public abstract class Task {
    private String task;
    private boolean isCompleted;

    public Task(String task) throws IllegalArgumentException {
        if (task.isEmpty() || task.equals(" ")){
            throw new IllegalArgumentException("\tOh no!!(0o0) Description cannot be empty");
        }
        this.task = task;
        this.isCompleted = false;
    }

    public Task(String task, Boolean isCompleted) throws IllegalArgumentException {
        if (task.isEmpty() || task.equals(" ")){
            throw new IllegalArgumentException("\tOh no!!(0o0) Description cannot be empty");
        }
        this.task = task;
        this.isCompleted = isCompleted;
    }

    public void setCompleted(boolean isCompleted)
            throws ChatBotException {
        if (this.isCompleted == isCompleted) {
            throw new ChatBotException("\tIt seems the task has already been marked as such");
        }
        this.isCompleted = isCompleted;
    }

    @Override
    public String toString() {
        return isCompleted ? "[X] " + task : "[ ] " + task;
    }

    public String toPrint() {
        return isCompleted ? "|1|" + task : "|0|" + task;
    }
}
