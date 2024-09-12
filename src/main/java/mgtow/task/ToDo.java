package mgtow.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents a todo task in the MGTOW application.
 * This class extends the Task class.
 */
public class ToDo extends Task {
    public ToDo(String desc) {
        super(desc, "T");
    }

    @Override
    public boolean isOnDate(LocalDate date) {
        return false;
    }

    @Override
    public LocalDateTime getDateTime() {
        return null; // ToDo tasks don't have a specific date/time
    }
}