package vuewee.task;

import java.util.regex.Pattern;

public class DeadlineTask extends Task {
    private TaskLocalDate by;

    private static final int EXPECTED_DELIMETED_PARAM_COUNT = 3;

    DeadlineTask() {
        super();
        this.type = TaskType.DEADLINE;
    }

    public DeadlineTask(String description, TaskLocalDate by) {
        super(description, TaskType.DEADLINE);
        this.by = by;
    }

    @Override
    String serialize() {
        return this.type.toChar() + Task.DELIMETER_SPACE + (this.isDone ? "1" : "0") + Task.DELIMETER_SPACE
                + this.description.replace(Task.DELIMETER, "\\" + Task.DELIMETER) + Task.DELIMETER_SPACE
                + this.by.serialize();
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
        this.by = TaskLocalDate.deserialize(parts[2]);
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.by.toString() + ")";
    }
}
