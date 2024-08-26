import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private final String task;
    private final LocalDateTime by;

    public Deadline(String str) throws TaskException {
        try {
            String[] temp = str.split("/by ");
            task = temp[0].split(" ", 2)[1];
            by = super.parseDateTime(temp[1]);
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new TaskException("deadline <task> /by <date> <time>");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + task + "(by: "
                + by.format(DateTimeFormatter.ofPattern("d LLL uuuu h.mma")) + ")";
    }
}
