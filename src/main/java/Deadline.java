import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime by;
    public Deadline(String description, String by) throws SamException {
        super(description, TaskType.DEADLINE);
        try {
            this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeException e) {
            throw new SamException("Invalid date format! Please use yyyy-MM-dd HHmm.\n " +
                    "Example call: deadline your_task /by yyyy-MM-dd HHmm");
        }

    }

    private String getBy() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return by.format(formatter);
    }

    private String getMeaningfulBy() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return by.format(formatter);
    }

    @Override
    public String toStorageString() {
        if (this.isDone()) {
            return "D | 1 | " + this.getDescription() + " | " + this.getBy();
        } else {
            return "D | 0 | " + this.getDescription() + " | " + this.getBy();
        }
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.getMeaningfulBy() + ")";
    }
}
