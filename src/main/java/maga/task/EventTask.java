package maga.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a specific date.
 * An event task has a description, a completion status, and a date.
 */
public class EventTask extends Task {
    protected LocalDate localDate;

    /**
     * Constructs an {@code EventTask} with the specified description, completion status, and date.
     *
     * @param isDone     Indicates if the task is completed.
     * @param description The description of the task.
     * @param localDate   The date of the event.
     */
    public EventTask(boolean isDone, String description, LocalDate localDate) {
        super(description);
        this.isDone = isDone;
        this.localDate = localDate;
    }

    /**
     * Returns the type of the task as a string.
     *
     * @return A string representing the task type, fixed as "[E]" for event tasks.
     */
    public String getTaskType() {
        return "[E]";
    }

    /**
     * Returns a formatted string representation of the event task for storage into the {@code Maga.txt} file.
     *
     * @return A string representation of the event task.
     */
    @Override
    public String toString() {
        int isDoneNum = 0;
        if (isDone) {
            isDoneNum = 1;
        }
        return "E | " + isDoneNum + " | " + description + " | " + localDate.toString() + " | " + getTag();
    }

    /**
     * Returns a formatted string representation of the event task for display purposes.
     *
     * @return A formatted string representation of the event task.
     */
    @Override
    public String printTask() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MMM dd");
        String formattedLocalDate = localDate.format(formatter);
        return getTaskType() + getStatusIcon() + getDescription() + " on " + formattedLocalDate + " " + getTag();
    }
}
