package tasks;

import tasks.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    LocalDate startDate;
    LocalDate endDate;

    public Event(String name, LocalDate startDate, LocalDate endDate) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
    }
    @Override
    public String saveTask() {

        return String.format("E|%s|%s|%s|%s", super.getStatus(), super.getTask(), startDate.format(DateTimeFormatter.ofPattern("dd-MM-yy")), endDate.format(DateTimeFormatter.ofPattern("dd-MM-yy")));
    }


    @Override
    public String toString() {
        return String.format("[E] %s (from: %s to: %s)", super.toString(), startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")), endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
