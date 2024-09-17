package weeny.task;

import java.time.LocalDate;

/**
 * Represents a to-do task with no specific date or time.
 */
public class Todo extends Task {

    /**
     * Creates a new to-do task with the specified description.
     *
     * @param description The description of the to-do task.
     */
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    /**
     * Returns a string representation of the to-do task for output.
     *
     * @return A string with task details in the format "T | checkMark | description".
     */
    public String toOutput() {
        int checkMark = this.isDone ? 1 : 0;
        return "T | " + checkMark + " | " + this.description;
    }

    /**
     * Overides method in Task
     *
     * @param date Current date
     * @return True because date is not relevant
     */
    @Override
    public boolean isOnDate(String date) {
        return true;
    }

    /**
     * Returns a string representation of the to-do task.
     *
     * @return A string in the format "[T][statusIcon] description".
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
