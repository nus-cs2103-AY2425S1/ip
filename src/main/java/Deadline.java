import java.time.LocalDateTime;

public class Deadline extends Task {

    private final LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, Task.dtf1);
    }

    @Override
    public String toFileFormat() {
        return "D" + super.toFileFormat() + " | " + this.by.format(Task.dtf1);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(Task.dtf2) + ")";
    }

}
