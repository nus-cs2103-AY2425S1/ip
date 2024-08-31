import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate byDate;

    public Deadline (String taskName, LocalDate byDate) {
        super(taskName);
        this.byDate = byDate;
    }

    public Deadline (String taskName, LocalDate byDate, boolean isDone) {
        super(taskName, isDone);
        this.byDate = byDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return String.format("[D]%s (by: %s)",
                super.toString(), this.byDate.format(formatter));
    }

    @Override
    public String commaString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return String.format("D,%s,%s",
                super.commaString(), this.byDate);
    }
}
