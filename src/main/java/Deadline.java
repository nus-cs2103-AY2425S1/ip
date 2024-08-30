import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate by;
    public Deadline(String name, String by) {
        super(name);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String getSaveFormat() {
        return "D | " + super.getSaveFormat() + " | " + this.by;
    }

    @Override
    public boolean isDuring(String date) {
        return this.by.isEqual(LocalDate.parse(date));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() +
                String.format(" (by: %s)", this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
