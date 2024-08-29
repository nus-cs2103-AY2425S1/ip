package jeff.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    public Event(String task, LocalDateTime from, LocalDateTime to) {
        super(task);
        this.from = from;
        this.to = to;
    }

    public String fromDateToString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return from.format(formatter);
    }

    public String toDateToString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return to.format(formatter);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.fromDateToString() + " to: " + this.toDateToString() + ")";
    }

    @Override
    public String saveAsCSV() {
        return "E," + super.saveAsCSV() + "," + this.fromDateToString() + "," + this.toDateToString();
    }
}
