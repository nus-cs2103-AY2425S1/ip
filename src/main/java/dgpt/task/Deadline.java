package dgpt.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    private DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d MMM yyyy");
    private LocalDate dueDate;

    public Deadline(String description, String dueDate) throws DateTimeParseException {
        super(description);
        this.dueDate = LocalDate.parse(dueDate, inputFormatter);
    }

    public String getDueDateString() {
        return this.dueDate.format(outputFormatter);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDueDateString() + ")";
    }
}
