public class Event extends Task {
    private final String startDateTime;
    private final String endDateTime;

    /**
     * Constructor for Deadline class.
     *
     * @param description   Description of the task.
     * @param startDateTime Start date and time of the event.
     * @param endDateTime   End date and time of the event.
     */
    public Event(String description, String startDateTime, String endDateTime) {
        super(TaskType.EVENT, description);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Serializes the event to a string.
     * The string is in the format of "E | 1 | description | startDateTime | endDateTime".
     * E is the task type, 1 is 1 if the task is done, description is the description of the task,
     * startDateTime is the start date and time of the event, endDateTime is the end date and time of the event.
     * The task type, is done status, description, start date and time and end date and time are separated by " | ".
     * The task type is represented by the first character of the task type.
     * For example, if the task type is Event, the task type is E.
     * If the task is done, the second character is 1, otherwise it is 0.
     * The description, start date and time and end date and time are the description, start date and time and end date and time of the task respectively.
     * The task type, is done status, description, start date and time and end date and time are separated by " | ".
     *
     * @return Serialized event.
     */
    @Override
    public String serialize() {
        return super.serialize() + " | " + startDateTime + " | " + endDateTime;
    }

    /**
     * Returns the string representation of the event.
     *
     * @return String representation of the event.
     */
    @Override
    public String toString() {
        return super.toString() + " (from: " + startDateTime + " to: " + endDateTime + ")";
    }
}
