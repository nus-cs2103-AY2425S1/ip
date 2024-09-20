package jbot.task;

/**
 * Represents a task with a time period between a start and end.
 * An <code>EventTask</code> includes a name, start time, and end time for the event.
 */
public class EventTask extends Task {

    private final String from;
    private final String to;

    /**
     * Constructs an <code>EventTask</code> with the specified input string.
     * The input string must include the task name, start time, and end time in the format
     * 'event /from [start] /to [end]'.
     *
     * @param input The input string containing the task details, start time, and end time.
     */
    public EventTask(String input) {
        this.setTaskTypeSymbol("E");

        int fromIndex = input.indexOf("/from");
        int toIndex = input.indexOf("/to");

        this.setName(input.substring(6, fromIndex).trim());
        this.from = input.substring(fromIndex + 6, toIndex).trim();
        this.to = input.substring(toIndex + 4).trim();
    }

    /**
     * Returns a string representation of the event task.
     * The format is the task's super string representation followed by the start and end times.
     *
     * @return A string representation of the event task.
     */
    @Override
    public String toString() {
        return String.format("%1$s (from: %2$s to: %3$s)", super.toString(), this.getFrom(), this.getTo());
    }

    /**
     * Returns the start time of the event.
     *
     * @return The start time of the event.
     */
    public String getFrom() {
        return this.from;
    }

    /**
     * Returns the end time of the event.
     *
     * @return The end time of the event.
     */
    public String getTo() {
        return this.to;
    }
}
