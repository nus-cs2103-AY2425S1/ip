package bobbybot.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import bobbybot.BobbyBotException;

/**
 * Represents a deadline task.
 * A deadline task has a description and a deadline.
 */
public class Deadline extends Task {

    public static final String TASK_TYPE = "D";

    private static final DateTimeFormatter DEADLINE_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");
    private final LocalDate by;

    /**
     * Creates a new Deadline object.
     *
     * @param description Description of the task.
     * @param by          Deadline of the task.
     * @throws BobbyBotException If the date format is invalid.
     */
    public Deadline(String description, String by) throws BobbyBotException {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new BobbyBotException("Please enter a valid date in the format yyyy-mm-dd.");
        }
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", TASK_TYPE, super.toString(), by.format(DEADLINE_DATE_FORMAT));
    }

    @Override
    public String getFileString() {
        return TASK_TYPE + " | " + (isDone() ? "1" : "0") + " | " + getDescription() + " | " + by;
    }
}
