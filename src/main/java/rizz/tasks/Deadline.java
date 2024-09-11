package rizz.tasks;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime by;
    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
    DateTimeFormatter readFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public Deadline(String text, LocalDateTime by, boolean isDone) {
        super(text,isDone);
        this.by = by;
    }


    @Override
    public String export() {
        return "D | " + (isDone ? "1" : "0") + " | " + text + " | " + by.format(readFormatter);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +  by.toLocalDate().format(outputFormatter) + ")";
    }
}