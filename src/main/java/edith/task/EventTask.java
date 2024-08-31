package edith.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventTask extends Task {
    private String start;
    private String end;

    public EventTask(String taskName, String start, String end) {
        super(taskName);
        try {
            LocalDateTime startInDateTimeFormat = LocalDateTime.parse(start);
            this.start = startInDateTimeFormat.format(DateTimeFormatter.ofPattern("d MMM yyyy h:mma"));
        } catch (DateTimeParseException e) {
            try {
                LocalDate startInDateFormat = LocalDate.parse(start);
                this.start = startInDateFormat.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
            } catch (DateTimeParseException e1) {
                try {
                    LocalTime startInTimeFormat = LocalTime.parse(start);
                    this.start = startInTimeFormat.format(DateTimeFormatter.ofPattern("h:mma"));
                } catch (DateTimeParseException e2) {
                    this.start = start;
                }
            }
        }

        try {
            LocalDateTime endInDateTimeFormat = LocalDateTime.parse(end);
            this.end = endInDateTimeFormat.format(DateTimeFormatter.ofPattern("d MMM yyyy h:mma"));
        } catch (DateTimeParseException e) {
            try {
                LocalDate endInDateFormat = LocalDate.parse(end);
                this.end = endInDateFormat.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
            } catch (DateTimeParseException e1) {
                try {
                    LocalTime endInTimeFormat = LocalTime.parse(end);
                    this.end = endInTimeFormat.format(DateTimeFormatter.ofPattern("h:mma"));
                } catch (DateTimeParseException e2) {
                    this.end = end;
                }
            }
        }
    }

    @Override
    public String toFileFormat() {
        String divider = " | ";
        String status = this.getStatus() ? "1" : "0";
        return "E" + divider + status + divider + this.getTaskName() + divider + this.start + divider + this.end;
    }

    @Override
    public String toString() {
        if (this.getStatus()) {
            return "[E][X] " + this.getTaskName() + " (from: " + this.start + " to: " + this.end + ")";
        } else {
            return "[E][ ] " + this.getTaskName() + " (from: " + this.start + " to: " + this.end + ")";
        }
    }
}
