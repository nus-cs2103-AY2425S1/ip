package task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String taskStringToSave;

    public Event(String task, String startTimeString, String endTimeString) {
        super("[E] ", task);
        this.startTime = parseTime(startTimeString);
        this.endTime = parseTime(endTimeString);
        this.taskStringToSave = task + " /from " + startTimeString + " /to " + endTimeString;
    }

    private LocalDateTime parseTime(String timeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return LocalDateTime.parse(timeString, formatter);
    }

    public LocalDate getStartTime() {
        return this.startTime.toLocalDate();
    }

    public LocalDate getEndTime() {
        return this.endTime.toLocalDate();
    }

    @Override
    public String savedTaskString() {
        return taskStringToSave;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, d MMM yyyy, ha", Locale.ENGLISH);
        String formattedStartTime = this.startTime.format(formatter);
        String formattedEndTime = this.endTime.format(formatter);
        String string = " (from: " + formattedStartTime + " - to: " + formattedEndTime + ")";
        return typeOfTaskString() + statusString() + taskString() + string;
    }
}
