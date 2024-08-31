package chatbot.util;

import chatbot.util.Task;

public class Todo extends Task {

    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    @Override
    public String getTaskInfo() {
        return "TODO|" + String.valueOf(isDone ? 1 : 0) + "|" + this.description + "\n";
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
