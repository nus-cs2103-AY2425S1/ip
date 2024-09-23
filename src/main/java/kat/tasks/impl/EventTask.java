package kat.tasks.impl;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import kat.exceptions.InvalidMessageException;

/**
 * Represents a task with a start and end time.
 */
public class EventTask extends AbstractTask {

    private final LocalDate startTime;

    private final LocalDate endTime;

    /**
     * Constructs a new EventTask with the given description, start time, and end time.
     *
     * @param description The description of the task.
     * @param startTime   The start time of the event in yyyy-mm-dd format.
     * @param endTime     The end time of the event in yyyy-mm-dd format.
     * @throws InvalidMessageException if the date format is invalid.
     */
    public EventTask(String description, String startTime, String endTime) throws InvalidMessageException {
        super(description);

        try {
            this.startTime = LocalDate.parse(startTime);
            this.endTime = LocalDate.parse(endTime);
        } catch (DateTimeParseException e) {
            throw new InvalidMessageException("Sorry, date should be in yyyy-mm-dd format. :(");
        }
    }

    /**
     * Deserializes an EventTask from a string representation.
     *
     * @param line The string representation of the EventTask.
     * @return The deserialized EventTask object.
     * @throws IllegalArgumentException if the task format is invalid.
     */
    public static EventTask deserialize(String line) {
        String[] parts = line.split(" \\| ");

        if (parts.length != 5 || !parts[0].equals("E")) {
            throw new IllegalArgumentException("Invalid EventTask format");
        }

        EventTask eventTask = null;
        try {
            eventTask = new EventTask(parts[2], parts[3], parts[4]);
            eventTask.setDone(parts[1].equals("1"));
        } catch (InvalidMessageException e) {
            e.printStackTrace();
        }

        return eventTask;
    }

    @Override
    public String serialize() {
        return String.format("E | %d | %s | %s | %s", isDone ? 1 : 0, description, startTime, endTime);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(),
                startTime.format(DATE_OUTPUT_FORMAT),
                endTime.format(DATE_OUTPUT_FORMAT));
    }

}
