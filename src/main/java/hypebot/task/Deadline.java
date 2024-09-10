package hypebot.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static hypebot.common.Messages.DUE_DATE_PARSE_ERROR;
import static hypebot.common.Messages.DUE_DATE_PASSED_ERROR;

/**
 * Represents a Deadline type Task with a LocalDateTime type due date.
 *
 * @author Youngseo Park (@youngseopark05)
 */
public class Deadline extends Task {
    private LocalDate dueDate;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Creates a Deadline task with the specified name and deadline.
     *
     * @param name The name of the deadline.
     * @param dueDateString The due date of the deadline in String form.
     * @throws DueDateParseException
     */
    public Deadline(String name, String dueDateString) throws DueDateParseException, IllegalArgumentException {
        super(name);
        try {
            LocalDate tempDate = LocalDate.parse(dueDateString, formatter);
            if (tempDate.isBefore(LocalDate.now())) {
                throw new IllegalArgumentException(DUE_DATE_PASSED_ERROR);
            }
            dueDate = tempDate;
        } catch (DateTimeParseException e) {
            throw new DueDateParseException(DUE_DATE_PARSE_ERROR, e.getParsedString(), e.getErrorIndex());
        }
    }

    /**
     * Takes in a LocalDate object representing a search date
     * and returns whether the Deadline is happening on the given date.
     *
     * @param date LocalDate object representing a date.
     * @return Whether the Deadline is happening on the given date.
     */
    @Override
    public boolean isHappeningOn(LocalDate date) {
        return dueDate.isEqual(date);
    }

    /**
     * Returns the String description of the task to append to /data/tasklist.txt.
     * Should be in this form: "D , {0 if not complete, 1 if complete} , {name} , {dueDate}".
     *
     * @return String description of Deadline task to append to /data/tasklist.txt.
     */
    @Override
    public String toFileString() {
        return "D , " + (isComplete() ? 1 : 0) + " , " + getName() + " , " + dueDate.format(formatter) + "\n";
    }

    /**
     * Returns the String representation of the Deadline task as shown to the user on the HypeBot UI.
     * Should be in this form: "[D][{X only if complete}] {name} (by: {dueDate})".
     *
     * @return String representation of Deadline task as shown on HypeBot UI.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: "
                + dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
