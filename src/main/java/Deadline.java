import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Deadline extends Task {
    private final LocalDateTime date;

    Deadline(String name, LocalDateTime date) {
        super(name);
        this.date = date;
    }

    Deadline(boolean isDone, String name, LocalDateTime date) {
        super(isDone, name);
        this.date = date;
    }

    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return String.format(
                "[D]%s (by: %s)",
                super.toString(), this.date.format(dtf));
    }

    public String convertToTxt() {
        return String.format("%s,%s,%s","D", super.convertToTxt(), this.date);
    }
}
