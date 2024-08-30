package alex.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    public Deadline(String description, LocalDate dueDate) {
        super(description);
        this.dueDate = dueDate;
    }
    @Override
    public String toBeSavedAsData() {
        return "[D][" + getStatusIcon() + "] " + super.toString() + " // by: " + dueDate;
    }
    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + super.toString() + " // by: " + dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) ;
    }
}
