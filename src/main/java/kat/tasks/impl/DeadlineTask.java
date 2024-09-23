package kat.tasks.impl;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import kat.exceptions.InvalidMessageException;

/**
 * Represents a task with a deadline.
 */
public class DeadlineTask extends AbstractTask {

    private final LocalDate deadline;

    /**
     * Constructs a new DeadlineTask with the given description and deadline.
     *
     * @param description The description of the task.
     * @param deadline    The deadline of the task in yyyy-mm-dd format.
     * @throws InvalidMessageException if the date format is invalid.
     */
    public DeadlineTask(String description, String deadline) throws InvalidMessageException {
        super(description);

        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            throw new InvalidMessageException("Sorry, date should be in yyyy-mm-dd format. :(");
        }
    }

    /**
     * Deserializes a DeadlineTask from a string representation.
     *
     * @param line The string representation of the DeadlineTask.
     * @return The deserialized DeadlineTask object.
     * @throws IllegalArgumentException if the task format is invalid.
     */
    public static DeadlineTask deserialize(String line) {
        String[] parts = line.split(" \\| ");

        if (parts.length != 4 || !parts[0].equals("D")) {
            throw new IllegalArgumentException("Invalid DeadlineTask format");
        }

        DeadlineTask deadlineTask = null;
        try {
            deadlineTask = new DeadlineTask(parts[2], parts[3]);
            deadlineTask.setDone(parts[1].equals("1"));
        } catch (InvalidMessageException e) {
            e.printStackTrace();
        }

        return deadlineTask;
    }

    @Override
    public String serialize() {
        return String.format("D | %d | %s | %s", isDone ? 1 : 0, description, deadline);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadline.format(DATE_OUTPUT_FORMAT));
    }

}
