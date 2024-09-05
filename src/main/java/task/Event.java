package task;

import babygronk.CustomDate;

/**
 * Task child class with event start time and event end time.
 */
public class Event extends Task {
    private static final String TASK_TYPE = "[E]";
    private CustomDate from;
    private CustomDate to;

    /**
     * Constructor for event task.
     *
     * @param task Task name.
     * @param from Event begin time.
     * @param to Event end time.
     */
    public Event(String task, String from, String to) {
        super(task);
        this.from = new CustomDate(from);
        this.to = new CustomDate(to);
    }

    @Override
    public String display() {
        return (TASK_TYPE + super.toString() + " (from: " + from + " to: " + to + ")");
    }

    @Override
    public String toString() {
        return (TASK_TYPE + super.toString()
                + " (from: " + from.unparseDate() + " to: " + to.unparseDate() + ")");
    }
}
