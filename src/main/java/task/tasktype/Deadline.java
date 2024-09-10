package task.tasktype;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import task.Task;


/**
 * Represents a deadline task that needs to be completed by a specific date and optionally, time.
 * This class extends the {@link Task} class.
 */
public class Deadline extends Task {

    protected LocalDate deadlineDate;
    protected LocalTime deadlineTime;

    /**
     * Constructs a new {@code deadline} task with the specified description and deadline
     *
     * @param description A description of the deadline task
     * @param by date that the task needs to be finished by, given in {@code d/M/yyyy} format (e.g. 25/10/2015)
     */
    public Deadline(String description, String by) {
        super(TaskType.DEADLINE, description);

        assert !by.isEmpty() : "every deadline task must have a due date";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        String[] deadlineArr = by.trim().split(" ");
        deadlineDate = LocalDate.parse(deadlineArr[0], formatter);
        if (deadlineArr.length == 2) {
            String timeStr = deadlineArr[1];
            if (timeStr.length() == 4) {
                timeStr = timeStr.substring(0, 2) + ":" + timeStr.substring(2);
            }
            deadlineTime = LocalTime.parse(timeStr);
        }
    }

    @Override
    public String toString() {
        String altFormat = deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String timeStr = deadlineTime == null ? "" : deadlineTime.toString();
        String deadline = (altFormat + " " + timeStr).trim();
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
