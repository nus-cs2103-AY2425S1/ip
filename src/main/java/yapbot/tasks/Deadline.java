package yapbot.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import yapbot.exceptions.YapBotException;

/**
 * Child class of Task that has an additional deadline field.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Creates a Deadline instance that is set to incomplete by default.
     *
     * @param description Details of the Task.
     * @param deadlineStr Date/time when this task should be completed by.
     * @throws YapBotException If task description and/or deadline is empty.
     */
    public Deadline(String description, String deadlineStr) throws YapBotException {
        super(description);

        if (deadlineStr.isEmpty()) {
            throw new YapBotException("Error, Deadline Prediction module offline."
                    + "\nManually input a deadline or use command \"todo\" for tasks without deadlines.");
        }

        LocalDateTime deadline;

        // Converts string to LocalDateTime
        if (deadlineStr.contains("AM") | deadlineStr.contains("PM")) {
            if (deadlineStr.contains("/")) {
                // Date and Time
                deadline = LocalDateTime.parse(deadlineStr, DATETIME_FORMATTER);
            } else {
                //Time only, sets date to the day's date
                deadline = LocalTime.parse(deadlineStr, TIME_FORMATTER).atDate(LocalDate.now());
            }
        } else {
            // Date only, set time to default to 8am
            deadline = LocalDate.parse(deadlineStr, DATE_FORMATTER).atTime(8, 0);
        }

        this.deadline = deadline;
    }

    /**
     * Creates a Deadline instance and allows completion status to be initialised.
     * This is mostly used when creating the task from a saved format.
     *
     * @param description Details of the Task.
     * @param deadline Date/time when this task should be completed by.
     * @param isDone Completion status the task will be initialized with.
     * @throws YapBotException If task description is empty.
     */
    public Deadline(String description, LocalDateTime deadline, boolean isDone) throws YapBotException {
        super(description);
        this.deadline = deadline;
        this.setDone(isDone);
    }

    @Override
    public String saveTask() {
        return "D/" + super.saveTask() + "/" + this.deadline.toString();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.format(OUTPUT_FORMATTER) + ")";
    }

}
