package Tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;
    private static final DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by, INPUT_FORMATTER);
    }

    @Override
    public String fileFormat() {
        return "D | " + super.fileFormat() + " | " + this.by.format(INPUT_FORMATTER);
    }


    public String fileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + this.description + " | " + by.toString();
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DISPLAY_FORMATTER) + ")";
    }
    //print in mmm dd yyyy format
}
