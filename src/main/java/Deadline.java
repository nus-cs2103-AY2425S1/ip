import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected LocalDateTime byTime;

    public Deadline(String description, String byTime, boolean isDone) throws DarkpoolException {
        super(description, isDone);
        try {
            this.byTime = LocalDateTime.parse(byTime, formatter);
        } catch (DateTimeParseException e) {
            throw new DarkpoolException("know what a date is?");
        }
    }

    @Override
    public String toString() {
        return "[D]" + (isDone ? "[X] " : "[ ] ") + this.description + " (by:" + this.byTime.format(formatter) + ")";
    }

    @Override
    public String toFileString() {
        return ("D | " + (isDone ? "1" : "0") + " | " + this.description + " | " + this.byTime.format(formatter) + "\n");
    }

}
