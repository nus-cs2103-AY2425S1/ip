package cypherchatbot.task;


public abstract class Task {
    protected String description;
    protected Boolean completed;

    protected final Integer TASK_PRIORITY;

    public Task(String desc, Integer priority) {
        this.description = desc.trim();
        this.TASK_PRIORITY = priority;
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
