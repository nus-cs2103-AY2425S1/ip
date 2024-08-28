import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description, TaskType.DEADLINE);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

        this.by = LocalDate.parse(by, formatter);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")) + ")";
    }
}
