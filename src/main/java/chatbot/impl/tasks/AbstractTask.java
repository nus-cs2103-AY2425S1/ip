package chatbot.impl.tasks;

import chatbot.Task;

public abstract class AbstractTask implements Task {

    protected final String description;

    protected boolean isDone;

    public AbstractTask(String description) {
        this.description = description;
        isDone = false;
    }

    @Override
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ", description);
    }

}
