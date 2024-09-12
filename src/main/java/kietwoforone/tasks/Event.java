package kietwoforone.tasks;

import kietwoforone.exceptions.KieTwoForOneException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event that has a start and end time.
 */
public class Event extends Task{

    private LocalDate startDate;
    private String startTime;
    private LocalDate endDate;
    private String endTime;
    private Task task;

    /**
     * Constructor for a new Event object.
     *
     * @param description
     * @param start
     * @param end
     */
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

    /**
     * Returns a string representation of the start date in the format "MMM d yyyy".
     *
     * @return Start date
     */
    public String getStartDate() {
        return this.startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns a string representation of the end date in the format "MMMM d yyyy".
     *
     * @return End date.
     */
    public String getEndDate() {
        return this.endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns a String representation of a marked event.
     *
     * @return Marked event.
     */
    @Override
    public String markTask() {
        return String.format("[E] %s (from: %s %s to: %s %s)", this.task.markTask(),
                this.getStartDate(), this.startTime, this.getEndDate(), this.endTime);
    }

    /**
     * Return a String representation of an unmarked event.
     *
     * @return Unmarked event.
     */
    @Override
    public String unmarkTask() {
        return String.format("[E] %s (from: %s %s to: %s %s)", this.task.unmarkTask() ,
                this.getStartDate(), this.startTime, this.getEndDate(), this.endTime);
    }

    /**
     * Returns a boolean value when comparing the deadline with the date string inputed.
     * Returns true if the date is within the start and end date inclusive, and false otherwise.
     * Throws a KieTwoForOne exception when the users input an incorrect format for the date.
     *
     * @param date
     * @return Boolean
     * @throws KieTwoForOneException
     */
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

    /**
     * Compares the details of the Event with the specified string input.
     * Returns true if the task contains the keyword, false otherwise.
     *
     * @param keyword
     * @return Boolean.
     */
    @Override
    public boolean compareString(String keyword) {
        return this.task.compareString(keyword);
    }

    /**
     * Returns the string representation of the Event object.
     *
     * @return String representation of the Event object.
     */
    @Override
    public String toString() {
        return String.format("[E] %s (from: %s %s to: %s %s)", this.task.toString(), this.getStartDate(), this.startTime, this.getEndDate(), this.endTime);
    }

}
