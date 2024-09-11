package tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Todos class represents a todo task.
 * This class extends the Task class and does not have a specific date associated with it.
 */

public class Todos extends Task {

    /**
     * Constructs a Todos task with the specified action.
     * The task is initially not completed.
     *
     * @param action The description of the task.
     */

    public Todos(String action) {
        super(action);
    }

    /**
     * Constructs a Todos task with the specified action and completion status.
     *
     * @param action The description of the task.
     * @param complete The completion status of the task.
     */

    public Todos(String action, boolean complete) {
        super(action, complete);
    }

    @Override
    public LocalDate getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime fillerDate = LocalDateTime.parse("06/06/0006 06:06", formatter);
        return fillerDate.toLocalDate();
    }

    @Override
    public String toString() {
        return "T | " + super.toString();
    }
}
