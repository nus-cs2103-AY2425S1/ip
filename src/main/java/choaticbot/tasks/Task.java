package choaticbot.tasks;

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

    public boolean containWord(String name) {
        assert name != null : "Search word should not be null";
        return this.name.contains(name);
    }

    @Override
    public String toString() {
        String marker = isCompleted ? "[X]" : "[ ]";
        return marker + " " + this.name;
    }
}