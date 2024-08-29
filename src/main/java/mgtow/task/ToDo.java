package mgtow.task;

import java.time.LocalDate;

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
}