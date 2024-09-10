package kietwoforone.tasks;

import kietwoforone.exceptions.KieTwoForOneException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    private LocalDate deadlineDate;
    private String deadlineTime;
    private Task task;

    public Deadline(String description, String deadline) {
        super(description);
        this.task = new Task(this.description);

        String[] time = deadline.replaceFirst("by ", "").split(" ", 2);
        this.deadlineDate = LocalDate.parse(time[0]);
        this.deadlineTime = time[1];
    }

    public String getDate() {
        return this.deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String markTask() {
        return String.format("[D] %s (by: %s %s)", this.task.markTask(), this.getDate(), this.deadlineTime);
    }

    @Override
    public String unmarkTask() {
        return String.format("[D] %s (by: %s %s)", this.task.unmarkTask(), this.getDate(), this.deadlineTime);
    }
    @Override
    public boolean compareDate(String date) throws KieTwoForOneException {
        try {
            return this.deadlineDate.equals(LocalDate.parse(date));
        } catch (DateTimeParseException e) {
            throw new KieTwoForOneException("Date must be valid and in the form YYYY-MM-DD!");
        }
    }

    @Override
    public boolean compareString(String keyword) {
        return this.task.compareString(keyword);
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", this.task.toString(), this.getDate(), this.deadlineTime);
    }

}
