package vuewee.task;

import java.util.regex.Pattern;

public class TodoTask extends Task {
    private static final int EXPECTED_DELIMETED_PARAM_COUNT = 2;

    TodoTask() {
        super();
        this.type = TaskType.TODO;
    }

    public TodoTask(String description) {
        super(description, TaskType.TODO);
    }

    public TodoTask(String description, boolean isDone) {
        super(description, TaskType.TODO, isDone);
    }

    @Override
    void deserialize(String serializedTask) {
        String[] parts = serializedTask.split(Pattern.quote(DELIMETER_SPACE), EXPECTED_DELIMETED_PARAM_COUNT);

        if (parts.length != EXPECTED_DELIMETED_PARAM_COUNT) {
            throw new IllegalArgumentException("Invalid task format: " + serializedTask);
        }

        boolean isDone = parts[0].equals("1");
        String description = parts[1].replace("\\" + DELIMETER, DELIMETER);

        this.description = description;
        this.isDone = isDone;
    }

    @Override
    String serialize() {
        return this.type.toChar() + Task.DELIMETER_SPACE + (this.isDone ? "1" : "0") + Task.DELIMETER_SPACE
                + this.description.replace(Task.DELIMETER, "\\" + Task.DELIMETER);
    }

}
