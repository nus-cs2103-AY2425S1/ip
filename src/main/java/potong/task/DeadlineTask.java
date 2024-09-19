package potong.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import potong.exceptions.IllegalInputPotongException;

/**
 * Represent the Deadline task with a deadline.
 */
public class DeadlineTask extends Task {

    private final String deadline;
    private final LocalDate date;

    /**
     * Initialise the Deadline Task.
     *
     * @param description Task description.
     * @param deadline Deadline of the task.
     * @throws IllegalInputPotongException If the task input is wrong.
     */
    public DeadlineTask(String description, String deadline) throws IllegalInputPotongException {
        super(description);
        if (deadline.isEmpty()) {
            throw new IllegalInputPotongException();
        }
        this.deadline = deadline;
        this.date = LocalDate.parse(deadline);
    }

    /**
     * Initialise the Deadline Task.
     *
     * @param description Task description.
     * @param deadline Deadline of the task.
     * @param isDone Whether the task is done.
     * @throws IllegalInputPotongException If the task input is wrong.
     */
    public DeadlineTask(String description, String deadline,
                        boolean isDone, String tag) throws IllegalInputPotongException {
        super(description, isDone, tag);
        if (deadline.isEmpty()) {
            throw new IllegalInputPotongException();
        }
        this.deadline = deadline;
        this.date = LocalDate.parse(deadline);
    }

    /**
     * Get the deadline of the task.
     *
     * @return String deadline.
     */
    @Override
    public String getTime() {
        return this.deadline;
    }

    /**
     * Get the type of the class.
     *
     * @return String "D".
     */
    @Override
    public String getType() {
        return "D";
    }

    /**
     * String representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                             this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
