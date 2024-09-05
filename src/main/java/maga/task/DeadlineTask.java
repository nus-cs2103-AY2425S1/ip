package maga.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task with a start and end date.
 * A deadline task has a description, a completion status, and a date range.
 */
public class DeadlineTask extends Task{
    protected LocalDate startDate;
    protected LocalDate endDate;

    /**
     * Constructs a {@code DeadlineTask} with the specified description, completion status, start date, and end date.
     *
     * @param isDone      Indicates if the task is completed.
     * @param description The description of the task.
     * @param startDate   The start date of the deadline.
     * @param endDate     The end date of the deadline.
     * @throws DateTimeParseException If the provided date strings cannot be parsed into {@code LocalDate}.
     */
    public DeadlineTask(boolean isDone, String description, LocalDate startDate, LocalDate endDate) throws DateTimeParseException {
        super(description);
        this.isDone = isDone;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Returns the type of the task as a string.
     *
     * @return A string representing the task type, set as "[D]" for deadline tasks.
     */
    public String getTaskType() {
        return "[D]";
    }

    /**
     * Returns a string representation of the deadline task.
     * The format is: "E | status | description | startDate | endDate" where
     * status is 1 if done, 0 otherwise, description is the task's description,
     * startDate and endDate are the start and end dates of the task.
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        int isDoneNum = 0;
        if (isDone) {
            isDoneNum = 1;
        }
        return "E | " + isDoneNum + " | " + description + " | " + startDate + " | " + endDate;
    }

    /**
     * Returns a formatted string representation of the deadline task for display purposes.
     * The format is: "[D] status description from startDate to endDate", where status is "[X]" if done,
     * "[ ]" otherwise, description is the task's description, and startDate and endDate are the task's dates
     * formatted as "yyyy MMM dd".
     *
     * @return A formatted string representation of the deadline task.
     */
    @Override
    public String printTask() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MMM dd");
        String formattedFrom = startDate.format(formatter);
        String formattedTo = endDate.format(formatter);
        return this.getTaskType() + this.getStatusIcon() + this.getDescription() + " from " + formattedFrom +
                " to " + formattedTo;
    }
}
