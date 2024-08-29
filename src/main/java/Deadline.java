import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends TaskWithDate {
    protected String by;
    protected LocalDate localDate;

    public Deadline(String name, LocalDate localDate) {
        super(name);
        this.localDate = localDate;
    }

    @Override
    public boolean overlapsWith(LocalDate localDate) {
        return localDate.equals(this.localDate);
    }

    @Override
    public String formatDate(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDate(this.localDate) + ")";
    }

    @Override
    public String parseTaskInfo() {
        return "D, "
                + (this.isCompleted ? "1, " : "0, ")
                + this.name + ", "
                + this.localDate
                + "\n";
    }
}
