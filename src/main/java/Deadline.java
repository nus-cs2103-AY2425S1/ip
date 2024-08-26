import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String by;
    protected LocalDateTime date;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, LocalDateTime date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        //return String.format("[D]" + super.toString() + " (by: %s)", date == null ? by : date);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        String formattedDate = (date != null) ? date.format(outputFormatter) : by;
        return String.format("[D]" + super.toString() + " (by: %s)", formattedDate);
    }

    @Override
    public String toFileFormat() {
        DateTimeFormatter converter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        String formattedDate = (date != null) ? date.format(converter) : by;
        return String.format("D | %d | %s | %s", isDone ? 1 : 0, description, formattedDate);
    }

}
