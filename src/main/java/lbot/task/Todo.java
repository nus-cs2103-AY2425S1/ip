package lbot.task;

public class Todo extends Task {
    private final String taskType = "[T]";

    public Todo(String description) {
        super(description);
        this.type = this.taskType;
    }

    public Todo(String description, boolean isComplete) {
        super(description, isComplete);
        this.type = "[T]";
    }

    @Override
    public String toString() {
        return this.type + "[" + this.status() + "] " + this.description;
    }
}
