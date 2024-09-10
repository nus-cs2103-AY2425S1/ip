package cypherchatbot.task;

public abstract class Task {
    protected String description;
    protected Boolean completed;

    public Task(String desc) {
        this.description = desc.trim();
        this.completed = false;
    }

    public void markAsComplete() {
        this.completed = true;
    }

    public void markAsIncomplete() {
        this.completed = false;
    }

    @Override
    public String toString() {
        String str = this.completed ? "[X] " : "[ ] ";
        str += this.description;
        return str;
    }

    public abstract String toStringInFile();
}
