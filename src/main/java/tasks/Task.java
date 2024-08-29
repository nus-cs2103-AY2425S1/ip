package tasks;

abstract public class Task {
    private String name;
    private Boolean isCompleted;


    public Task(String name) {
        this.name = name;
        this.isCompleted = false;
    }

    public void complete() {
        this.isCompleted = true;
    }

    public void uncomplete() {
        this.isCompleted = false;
    }

    public String getName() {
        return this.name;
    }

    public abstract String getType();

    public abstract String getAdditionalInfo();

    public boolean isComplete() {
        return this.isCompleted;
    }

    public void setDone(boolean isDone) {
        this.isCompleted = isDone;
    }

    public String toFileString() {
        return getType() + "|" + getName() + "|" + isComplete() + "|" + getAdditionalInfo();
    }

    @Override
    public String toString() {
        String marker = isCompleted ? "[X]" : "[ ]";
        return marker + " " + this.name;
    }

}