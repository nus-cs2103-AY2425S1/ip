package max.task;

import max.exception.MaxException;

/**
 * The Event class represents a task that starts and ends at specific times.
 * It extends the Task class and includes additional attributes for start and end times.
 */
public class Event extends Task {
    private String startTime;
    private String endTime;

    /**
     * Constructs an Event with a description and a time period.
     * The time period is split into a start time and an end time.
     *
     * @param description The description of the event.
     * @param time The time period of the event in the format "start time /to end time".
     * @throws MaxException If the time period does not contain both a start and end time.
     */
    public Event(String description, String time) throws MaxException {
        super(description);
        String[] temp = time.split(" /to ");
        if (temp.length != 2) {
            throw new MaxException("Oh no!! The description of the task cannot be empty. :(");
        }
        this.startTime = temp[0];
        this.endTime = temp[1];
    }

    /**
     * Returns a string representation of the event, including its type, description,
     * and time period.
     *
     * @return A string in the format "[E][status] description (from: start time to: end time)".
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime + " to: " + endTime + ")";
    }

    /**
     * Returns the task in a format suitable for saving to a file.
     * The format includes the task type, completion status, description, start time,
     * end time, and any tags associated with the task.
     *
     * @return A string representing the task formatted for file storage.
     */
    @Override
    public String toFileFormat() {
        String tagsString = getTagsString();
        return String.format("E | %d | %s | /from %s /to %s%s", isDone ? 1 : 0, description,
                startTime, endTime,
                tagsString.isEmpty() ? "" : " | " + tagsString);
    }

}
