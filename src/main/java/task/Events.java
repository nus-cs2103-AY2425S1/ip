package task;

import task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task {
    public LocalDateTime start;
    public LocalDateTime end;
    public Events(String description, LocalDateTime start, LocalDateTime end) {
        super(description + " (from: " + formatDate(start) + ", to: " + formatDate(end) + ")", TaskType.EVENT);
        this.start = start;
        this.end = end;
    }

    private static String localDateTimeString (LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        String formattedDate = date.format(formatter);
        return formattedDate;
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String toFileString() {
        int index = description.indexOf("(from:");
        return String.format("E | %d | %s | %s | %s", isDone ? 1 : 0, description.substring(0, index).trim(), localDateTimeString(start), localDateTimeString(end));
    }

    public static String formatDate(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        String formattedDate = date.format(formatter);
        return formattedDate;
    }
}
