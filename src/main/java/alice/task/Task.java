package alice.task;

import java.util.*;

public abstract class Task {
    protected String description;
    protected boolean isCompleted;

    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    public Task(String line) throws InvalidTaskException {
        this.description = Parser.parseDescription(line);
        this.isCompleted = false;
    }

    public void setCompletion(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public static Task fromJsonString(String jsonString) throws InvalidTaskException {
        Map<String, String> arguments = Parser.parseJsonString(jsonString);        
        switch (TaskType.valueOf(arguments.get("taskType").toUpperCase())) {
            case TODO:
                return ToDo.fromJsonString(jsonString);
            case DEADLINE:
                return Deadline.fromJsonString(jsonString);
            case EVENT:
                return Event.fromJsonString(jsonString);
            default:
                throw new InvalidTaskException();
        }
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", isCompleted ? "X" : " ", description);
    }

    public abstract String toJsonString();
}
