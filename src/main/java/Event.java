import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String desc, LocalDateTime from, LocalDateTime to) {
        super(desc);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toSaveFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");
        return "E | " + (isChecked ? "1 | " : "0 | ") + this.taskDesc + " | " + this.from.format(formatter) + " | " + this.to.format(formatter);
    }
}
