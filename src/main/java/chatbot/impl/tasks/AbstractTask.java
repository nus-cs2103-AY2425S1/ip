package chatbot.impl.tasks;

import chatbot.Task;

public abstract class AbstractTask implements Task {

    private final String name;

    private boolean isDone;

    public AbstractTask(String name) {
        this.name = name;
        isDone = false;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ", name);
    }

}
