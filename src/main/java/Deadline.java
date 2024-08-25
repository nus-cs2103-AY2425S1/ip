import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime dueDate;

    Deadline(String description, String symbol, LocalDateTime dueDate) {
        super(description, symbol);
        this.dueDate = dueDate;
    }

    /**
     * @return task's dueDate (deadline)
     */
    public String getDueDate() {
        String time = this.dueDate.format(DateTimeFormatter.ofPattern("HH:mm a"));
        String date = this.dueDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return date + " " + time;
    }

    @Override
    public String toString() {
        return super.toString() + " " + String.format("(by: %s)", this.dueDate);
    }
}
