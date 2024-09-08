package Tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    private LocalDate by;
    private String byString;

    public Deadline(String description, String by) {
        super(description);
        try {
            LocalDate d = LocalDate.parse(by);
            this.by = d;
        } catch (DateTimeParseException e) {
            this.byString = by;
        }
    }

    @Override
    public String toString() {
        return this.by == null
                ? String.format("[Deadline] " + super.toString() + " By: [%s]", this.byString)
                : String.format("[Deadline] " + super.toString() + " By: [%s]", this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
