package kietwoforone.tasks;

import kietwoforone.exceptions.KieTwoForOneException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task{

    private LocalDate startDate;
    private String startTime;
    private LocalDate endDate;
    private String endTime;
    private Task task;

    public Event(String description, String start, String end) {
        super(description);
        this.task = new Task(this.description);

        String[] beginning = start.replaceFirst("from ", "").split(" ", 2);
        this.startDate = LocalDate.parse(beginning[0]);
        this.startTime = beginning[1];

        String[] ending  = end.replaceFirst("to ", "").split(" ", 2);
        this.endDate = LocalDate.parse(ending[0]);
        this.endTime = ending[1];
    }

    public String getStartDate() {
        return this.startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String getEndDate() {
        return this.endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String markTask() {
        return String.format("[E] %s (from: %s %s to: %s %s)", this.task.markTask(),
                this.getStartDate(), this.startTime, this.getEndDate(), this.endTime);
    }

    @Override
    public String unmarkTask() {
        return String.format("[E] %s (from: %s %s to: %s %s)", this.task.unmarkTask() ,
                this.getStartDate(), this.startTime, this.getEndDate(), this.endTime);
    }

    @Override
    public boolean compareDate(String date) throws KieTwoForOneException {
        LocalDate newDate;
        try {
            newDate = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new KieTwoForOneException("Date must be valid and in the form YYYY-MM-DD!");
        }
        return (this.startDate.isBefore(newDate) && this.endDate.isAfter(newDate)) ||
                this.startDate.equals(newDate) || this.endDate.equals(newDate);
    }

    @Override
    public String toString() {
        return String.format("[E] %s (from: %s %s to: %s %s)", this.task.toString(), this.getStartDate(), this.startTime, this.getEndDate(), this.endTime);
    }

}
