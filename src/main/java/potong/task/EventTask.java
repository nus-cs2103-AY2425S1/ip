package potong.task;

import potong.exceptions.IllegalInputPotongException;

/**
 * Represent the Event task.
 */
public class EventTask extends Task {

    private final String start;
    private final String end;

    /**
     * Initialise the event task.
     *
     * @param description Task description.
     * @param start Task start time.
     * @param end Task end time.
     * @throws IllegalInputPotongException If the task input is wrong.
     */
    public EventTask(String description, String start, String end) throws IllegalInputPotongException {
        super(description);
        if (start.isEmpty() || end.isEmpty()) {
            throw new IllegalInputPotongException("Your start or end cannot be empty!");
        }
        this.start = start;
        this.end = end;
    }

    /**
     * Initialise the event task.
     *
     * @param description Task description.
     * @param start Task start time.
     * @param end Task end time.
     * @param isDone Whether the task is done.
     * @throws IllegalInputPotongException If the task input is wrong.
     */
    public EventTask(String description, String start,
                     String end, boolean isDone, String tag) throws IllegalInputPotongException {
        super(description, isDone, tag);
        if (start.isEmpty() || end.isEmpty()) {
            throw new IllegalInputPotongException("Your start or end cannot be empty!");
        }
        this.start = start;
        this.end = end;
    }

    /**
     * Get the duration for the task.
     *
     * @return String representation of the time of the task.
     */
    @Override
    public String getTime() {
        return this.start + "-" + this.end;
    }

    /**
     * Get the type of the task.
     *
     * @return String "E".
     */
    @Override
    public String getType() {
        return "E";
    }

    /**
     * String representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.start, this.end);
    }
}
