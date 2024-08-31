package orion.tasks;

import orion.exceptions.OrionException;
import orion.exceptions.OrionInputException;

import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * Represents a task with a deadline.
 * Extends the {@link Task} class to include a deadline date.
 */
public class Deadline extends Task {
    private LocalDate time;

    /**
     * Constructs a {@code Deadline} with the specified body and deadline.
     * The task is initialized as not done.
     *
     * @param body the description of the task
     * @param time the deadline of the task as a {@code LocalDate}
     */
    public Deadline(String body, LocalDate time) {
        super(body);
        this.time = time;
    }

    /**
     * Constructs a {@code Deadline} with the specified body and deadline string.
     * The task is initialized as not done.
     *
     * @param body the description of the task
     * @param deadline the deadline of the task as a string in the format "yyyy-mm-dd"
     * @throws OrionException if the deadline string cannot be parsed into a {@code LocalDate}
     */
    public Deadline(String body, String deadline) throws OrionException {
        super(body);
        try {
            this.time = LocalDate.parse(deadline);
        } catch (DateTimeException e) {
            throw new OrionInputException("Correct syntax: deadline <task> /by <yyyy-mm-dd>." +
                    " Please input a valid date in the correct format!");
        }
    }

    /**
     * Constructs a {@code Deadline} with the specified body, completion status, and deadline.
     *
     * @param body the description of the task
     * @param isDone the completion status of the task
     * @param time the deadline of the task as a {@code LocalDate}
     */
    public Deadline(String body, boolean isDone, LocalDate time) {
        super(body, isDone);
        this.time = time;
    }

    /**
     * Returns a string representation of the deadline in the format "day/month/year".
     *
     * @return the string representation of the deadline
     */
    private String getTimeString() {
        return this.time.getDayOfMonth() + "/" + this.time.getMonthValue() + "/" + this.time.getYear();
    }

    /**
     * Returns a string representation of the {@code Deadline}.
     * The format is "[D][task] (due by: day/month/year)".
     *
     * @return the string representation of the {@code Deadline}
     */
    @Override
    public String toString() {
        return String.format("[D]%s (due by: %s)", super.toString(), getTimeString());
    }

    /**
     * Returns a string representation of the {@code Deadline} for saving to a file.
     * The format is "deadline,[task],[isDone],[deadline date]".
     *
     * @return the string representation of the {@code Deadline} for saving
     */
    @Override
    public String saveString() {
        return "deadline," + super.saveString() + "," + this.time;
    }
}
