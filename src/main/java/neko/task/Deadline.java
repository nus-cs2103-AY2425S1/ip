package neko.task;
import java.time.LocalDateTime;

public class Deadline extends Task {

    private final LocalDateTime by;

    public Deadline(String name, LocalDateTime by) {
        super(name);
        this.by = by;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(dateFormatter) + ")";
    }
    @Override
    public String getTime() {
        return by.format(dateFormatter);
    }
}
