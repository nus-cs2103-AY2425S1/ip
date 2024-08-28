package mel.tasks;

import mel.exceptions.ParseException;
import mel.exceptions.TaskException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event class that represents a task with a
 * start and end date and/or time.
 */
public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructs new Event task.
     * @param input task input with a start and end date and/or time
     *              , in the format: event *task*
     *              /from *date* *time* /to *date* *time*.
     * @throws TaskException if input is of invalid format.
     */
    public Event(String input) throws TaskException {
        try {
            String[] temp = input.split("/from ");
            task = temp[0];

            String[] s = temp[1].split(" /to ");
            from = super.parseDateTime(s[0]);
            to = super.parseDateTime(s[1]);
            if (from.isAfter(to)) {
                throw new TaskException("event <task> "
                        + "/from <date> <time> /to <date> <time>\n"
                        + "Mel wonders if you control time..?");
            }
        } catch (ArrayIndexOutOfBoundsException | ParseException e) {
            throw new TaskException("event <task> "
                    + "/from <date> <time> /to <date> <time>");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + task + "(from: "
                + from.format(DateTimeFormatter.ofPattern("d LLL uuuu h.mma"))
                + " to: "
                + to.format(DateTimeFormatter.ofPattern("d LLL uuuu h.mma"))
                + ")";
    }
}
