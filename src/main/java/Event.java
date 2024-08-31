import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate fromDate;
    protected LocalDate toDate;

    public Event (String taskName, LocalDate fromDate, LocalDate toDate) {
        super(taskName);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public Event (String taskName, LocalDate fromDate, LocalDate toDate, boolean isDone) {
        super(taskName, isDone);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(), this.fromDate.format(formatter), this.toDate.format(formatter));
    }

    @Override
    public String commaString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return String.format("E,%s,%s,%s",
                super.commaString(), this.fromDate.format(formatter), this.toDate.format(formatter));
    }
}