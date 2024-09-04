package winner;

/**
 * Represents an Event task which includes the start and end of the event.
 */
public class Event extends Task {
    protected String start;
    protected String end;

    /**
     * Creates a new Event task with the given description, start and end of the event.
     *
     * @param description Description of the Event task.
     * @param start Start of Event.
     * @param end End of Event.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a String representation of an Event task, including its completion status, description, start and end.
     *
     * @return A String representing the Event task, including its completion status, description, start and end.
     */
    @Override
    public String taskToString() {
        if (isDone) {
            return "[E] [X] " + description + " (" + start + " - " + end + ")";
        }
        return "[E] [ ] " + description + " (" + start + " - " + end + ")";
    }

    /**
     * Marks an Event task as done and returns a String confirming the task has been marked as done.
     *
     * @return A String indicating that the task has been marked as done.
     */
    @Override
    public String markDone() {
        return super.markDone() + "\n"
                + " ".repeat(5) + "[E] [X] " + description + " (" + start + " - " + end + ")";
    }

    /**
     * Marks an Event task as undone and returns a String confirming the task has been marked as undone.
     *
     * @return A String indicating the task has been marked as undone.
     */
    @Override
    public String unmarkDone() {
        return super.unmarkDone() + "\n"
                + " ".repeat(5) + "[E] [ ] " + description + " (" + start + " - " + end + ")";
    }

    /**
     * Deletes an Event task and returns a String confirming the task has been deleted.
     *
     * @return A String indicating the task has been deleted.
     */
    @Override
    public String deleteTask() {
        if (isDone) {
            return super.deleteTask() + "\n"
                    + " ".repeat(5) + "[E] [X] " + description + " (" + start + " - " + end + ")";
        }
        return super.deleteTask() + "\n"
                + " ".repeat(5) + "[E] [ ] " + description + " (" + start + " - " + end + ")";
    }

}
