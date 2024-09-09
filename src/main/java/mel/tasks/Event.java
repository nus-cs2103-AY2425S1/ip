package mel.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import mel.exceptions.ParseException;
import mel.exceptions.TaskException;

/**
 * Event class that represents a task with a
 * start and end date and/or time.
 */
public class Event extends Task {
    private LocalDateTime from = null;
    private LocalDateTime to = null;

    /**
     * Constructs new Event task.
     * @param input task input with a start and end date and/or time
     *              , in the format: event *task*
     *              /from *date* *time* /to *date* *time*.
     * @throws TaskException if input is of invalid format.
     */
    public Event(String input) throws TaskException {
        try {
            String[] s = processInput(input);
            processDateTime(s);
            assert (from != null) && (to != null)
                    : "date/time should not be empty";
            if (from.isAfter(to)) {
                throw new TaskException("event <task> "
                        + "/from <date> <time> /to <date> <time>\n"
                        + "Mel wonders if you control time..?");
            } else {
                //Fallthrough: input is correct and valid
            }
        } catch (ArrayIndexOutOfBoundsException | ParseException e) {
            throw new TaskException("event <task> "
                    + "/from <date> <time> /to <date> <time>");
        }
    }
    private String[] processInput(String input) {
        String[] temp = input.split("/from ");
        task = temp[0];
        String[] eventTimes = temp[1].split(" /to ");
        return eventTimes;
    }

    private void processDateTime(String... input) throws ParseException {
        from = super.parseDateTime(input[0].trim());
        to = super.parseDateTime(input[1].trim());
    }

    @Override
    public String toString() {
        String mark = super.toString();
        String start = from.format(DateTimeFormatter.ofPattern("d LLL uuuu h.mma"));
        String end = to.format(DateTimeFormatter.ofPattern("d LLL uuuu h.mma"));
        return "[E]" + mark + task + "(from: " + start + " to: " + end + ")";
    }
}
