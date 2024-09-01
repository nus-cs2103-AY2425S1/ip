package jbot.task;

public abstract class Task {

    @SuppressWarnings("InstanceVariableMayNotBeInitialized")
    String name;
    private boolean done = false;
    @SuppressWarnings("InstanceVariableMayNotBeInitialized")
    String taskTypeSymbol;

    public boolean isDone() {
        return this.done;
    }
    public void markAsDone() {
        this.done = true;
    }

    public void unmarkAsDone() {
        this.done = false;
    }

    @Override
    public String toString() {
        String doneString = this.done ? "X" : " ";
        return String.format("[%1$s] [%2$s]  %3$s", this.getTaskTypeSymbol(), doneString, this.getName());
    }

    public String getTaskTypeSymbol() {
        return this.taskTypeSymbol;
    }

    public String getName() {
        return this.name;
    }
}