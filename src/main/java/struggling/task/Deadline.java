package struggling.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDate by;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by.format(formatter));
    }

    @Override
    public String getState() {
        return String.format("D | %s | %s", super.getState(), this.by.toString());
    }
}
