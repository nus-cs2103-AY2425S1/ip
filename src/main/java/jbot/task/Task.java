package jbot.task;

public abstract class Task {

    String name;
    private boolean done = false;
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
        String doneString = done ? "X" : " ";
        return String.format("[%1$s] [%2$s]  %3$s", this.getTaskTypeSymbol(), doneString, this.getName());
    }

    public String getTaskTypeSymbol() {
        return taskTypeSymbol;
    }

    public String getName() {
        return name;
    }
}