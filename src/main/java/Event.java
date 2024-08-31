import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String description, String from, String to) throws SamException {
        super(description, TaskType.EVENT);
        try {
            this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeException e) {
            throw new SamException("Invalid date format! Please use yyyy-MM-dd HHmm. \n" +
                    "Example call: event your_event /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm");
        }
    }

    private String getTime(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return time.format(formatter);
    }

    private String getMeaningfulTime(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm");
        return time.format(formatter);
    }

    @Override
    public String toStorageString() {
        if (this.isDone()) {
            return "E | 1 | " + this.getDescription() + " | " + this.getTime(from) + " | " + this.getTime(to);
        } else {
            return "E | 0 | " + this.getDescription() + " | " + this.getTime(from) + " | " + this.getTime(to);
        }
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + this.getMeaningfulTime(from) + " to: " + this.getMeaningfulTime(to) + ")";
    }
}
