package gravitas.task;

import java.time.format.DateTimeFormatter;

/**
 * Represents a Todo task.
 */
public class Todo extends Task {

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");

    public Todo(String description) {
        super(description, "T");
    }

    @Override
    public String getDescription() {
        return super.description;
    }

    @Override
    public String formatData() {
        String mark = this.isDone ? "1" : "0";
        return (this.eventType + " | " + mark + " | " + this.description + "\n");
    }
}
