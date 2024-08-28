import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate byDate;
    protected LocalDateTime byDateTime;
    protected DateTimeFormatter stringFormatter;
    protected DateTimeFormatter dataFormatter;

    public Deadline(String description, String by) throws LictException {
        super(description);
        String[] deadline = by.split("\\s+", 2);
        DateTimeFormatter formatter;
        try {
            if (deadline.length == 2) {
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                this.stringFormatter = DateTimeFormatter.ofPattern("MMM d yyyy h:mma");
                this.byDateTime = LocalDateTime.parse(by.trim(), formatter);
            } else {
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                this.stringFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");
                this.byDate = LocalDate.parse(by.trim(), formatter);
            }
            this.dataFormatter = formatter;
        } catch (DateTimeException e) {
            throw new LictException("Invalid format for deadline. Please ensure that deadline is in the form 'yyyy-MM-dd' or 'yyyy-MM-dd HHmm'.");
        }
    }

    @Override
    public String toString() {
        String by;
        if (this.byDate != null) {
            by = this.byDate.format(this.stringFormatter);
        } else {
            by = this.byDateTime.format(this.stringFormatter);
        }
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toData() {
        String by;
        if (this.byDate != null) {
            by = this.byDate.format(this.dataFormatter);
        } else {
            by = this.byDateTime.format(this.dataFormatter);
        }
        String status = this.isDone ? "1" : "0";
        return String.format("DEADLINE | %s | %s | %s\n", status, this.description, by);
    }
}
