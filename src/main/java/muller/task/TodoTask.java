package muller.task;

import java.time.LocalDate;

/**
 * Represents a Todo task without any specific date or time.
 */
public class TodoTask extends Task {

    /**
     * Constructs a TodoTask object with the specified name.
     *
     * @param name The name of the todo task.
     */
    public TodoTask(String name) {
        super(name);
        this.type = "[T]";
    }

    /**
     * Checks if the task occurs on a specified date.
     *
     * @param date The date to check.
     * @return False as Todo tasks do not have dates.
     */
    @Override
    public boolean isOnDate(LocalDate date) {
        return false;
    }

    /**
     * Checks if the task is due soon.
     *
     * @return False as Todo tasks do not have deadlines.
     */
    @Override
    public boolean isDueSoon() {
        return false;
    }

    /**
     * Converts the TodoTask to a string format suitable for saving to a file.
     *
     * @return The string representation of the TodoTask for saving.
     */
    @Override
    public String convertToFileString() {
        return "T | " + (isDone() ? "1" : "0") + " | " + getName();
    }
}

