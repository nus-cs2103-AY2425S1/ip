package mel.tasks;

import mel.exceptions.ParseException;
import mel.exceptions.TaskException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class that represents a task with a deadlines.
 */
public class Deadline extends Task {
    private final String task;
    private final LocalDateTime by;

    /**
     * Constructs new Deadline task.
     * @param input task input with a deadline, in the format:
     *              deadline *task* /by *date* *time*.
     * @throws TaskException if input is of invalid format.
     */
    public Deadline(String input) throws TaskException {
        try {
            String[] temp = input.split("/by ");
            task = temp[0].split(" ", 2)[1];
            by = super.parseDateTime(temp[1]);
        } catch (ArrayIndexOutOfBoundsException | ParseException e) {
            throw new TaskException("deadline <task> /by <date> <time>");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + task + "(by: "
                + by.format(DateTimeFormatter.ofPattern("d LLL uuuu h.mma")) + ")";
    }
}
