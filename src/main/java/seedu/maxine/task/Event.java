package seedu.maxine.task;

public class Event extends Task {

    protected String start;
    protected String end;
    public Event() {
        super();
    }

    /**
     * Constructs an Event with the specified description, start date, and end date.
     * <p>
     * The start and end dates are parsed using the {@code dateTimeParser}. If parsing fails,
     * the provided raw strings are used.
     * </p>
     *
     * @param description A brief description of the event.
     * @param start The start date and time of the event, in string format.
     * @param end The end date and time of the event, in string format.
     */
    public Event(String description, String start, String end) {
        super(description);
        try {
            this.start = dateTimeParser(start.trim());
            this.end = dateTimeParser(end.trim());
        } catch (Exception e) {
            this.start = start;
            this.end = end;
        }
    }


    /**
     * Returns a string representation of the event task in a human-readable format.
     * <p>
     * This format includes the task type indicator, its status icon, the description,
     * the start date and time, and the end date and time.
     * </p>
     *
     * @return A string representation of the event task formatted as "[E][status] description (From: start | To: end)".
     */
    @Override
    public String toString() {
        return "[E]" + getStatusIcon() + description
                + " (From: " + start + " | To: " + end + ")";
    }


    /**
     * Returns a string representation of the event task suitable for file storage.
     * <p>
     * This format includes a type indicator, status, description, start date, and end date.
     * It uses slashes to separate different parts of the task information.
     * </p>
     *
     * @return A string representation of the event task formatted as "E[status] / start / end" for file storage.
     */
    @Override
    public String writeToFile() {
        return "E" + super.writeToFile() + " / " + start + " / " + end;
    }

}

