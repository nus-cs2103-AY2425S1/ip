package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class EventTask extends Task {
    LocalDateTime start;
    LocalDateTime end;
    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a");

    public EventTask(String description, boolean isDone, String start, String end) {
        super(description, isDone);
        this.start = parseTime(start);
        this.end = parseTime(end);
    }

    private LocalDateTime parseTime(String time) throws DateTimeParseException {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return LocalDateTime.parse(time, inputFormatter);
    }

    @Override
    public String getTaskType() {
        return "E";
    }
    @Override
    public String getDescription() {
        return this.getTaskType() + " | " +
                super.getDescription().replace("\n", "") +
                "| " + String.format("from %s to %s",
                this.start.format(outputFormatter), this.end.format(outputFormatter));
    }

    @Override
    public String toString() {
        return String.format("%s | %s | %s | %s | %s",
                this.getTaskType(), getStatusIcon(), super.description,
                this.start.format(outputFormatter), this.end.format(outputFormatter));
    }
}
