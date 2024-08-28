package skywalker.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {

    public LocalDate by;

    public Deadline(String description, String by) {
        super(description, TaskType.DEADLINE);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.by = LocalDate.parse(by, formatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString() + "(by: " + by.format(formatter) + ")";
    }
}
