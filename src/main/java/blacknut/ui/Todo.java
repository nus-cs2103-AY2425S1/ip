package blacknut.ui;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * The Todo class represents a task with no specific deadline or event time.
 */

public class Todo extends Task {
    /**
     * Constructs a Todo object with the specified description.
     *
     * @param description The description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }


    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}