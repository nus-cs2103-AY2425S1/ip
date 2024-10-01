package flychat.tasks;

import java.util.InputMismatchException;

/**
 * Represents the Event task type.
 */
public class Event extends Task {
    private String startTime;
    private String endTime;

    private Event(String description, String startTime, String endTime) {
        super(description); /* isolates the task description */
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Creates a new event object.
     *
     * @param description A string containing the description of the event task.
     * @param startTime A string containing the start time of the event task.
     * @param endTime A string containing the end time of the event task.
     * @param isMarked A boolean indicating if the event task is marked.
     * @return A new Event task.
     * @throws InputMismatchException If input does not contain a description, startTime or endTime.
     */
    public static Event createNewEvent(String description, String startTime, String endTime, boolean isMarked) throws
            InputMismatchException {
        if (description.equals("") || startTime.equals("") || endTime.equals("")) {
            throw new InputMismatchException(
                    "Please ensure that the input contains a description, start and end time TT");
        }

        Event newEvent = new Event(description, startTime, endTime);
        if (isMarked) {
            newEvent.completeTask();
        }
        return newEvent;
    }

    @Override
    public String formatStringForSaving() {
        return "[E]" + super.toString() + " (from: " + this.startTime + " to: " 
                + this.endTime + ")";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }
}
