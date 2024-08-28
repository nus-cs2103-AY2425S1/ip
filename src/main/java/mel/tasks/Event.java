package mel.tasks;

import mel.exceptions.ParseException;
import mel.exceptions.TaskException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    public Event(String str) throws TaskException {
        try {
            String[] temp = str.split("/from ");
            task = temp[0];

            String[] s = temp[1].split(" /to ");
            from = super.parseDateTime(s[0]);
            to = super.parseDateTime(s[1]);
            if (from.isAfter(to)) {
                throw new TaskException("Mel wonders if you control time..?");
            }
        } catch (ArrayIndexOutOfBoundsException | ParseException e) {
            throw new TaskException("event <task> "
                    + "/from <date> <time> /to <date> <time>");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: "
                + from.format(DateTimeFormatter.ofPattern("d LLL uuuu h.mma")) + " to: "
                + to.format(DateTimeFormatter.ofPattern("d LLL uuuu h.mma")) + ")";
    }
}
