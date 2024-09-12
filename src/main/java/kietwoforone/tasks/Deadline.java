package kietwoforone.tasks;

import kietwoforone.exceptions.KieTwoForOneException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task that has a deadline time.
 */
public class Deadline extends Task {

    private LocalDate deadlineDate;
    private String deadlineTime;
    private Task task;

    /**
     * Constructor for a new Deadline object.
     *
     * @param description
     * @param deadline
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.task = new Task(this.description);

        String[] time = deadline.replaceFirst("by ", "").split(" ", 2);
        this.deadlineDate = LocalDate.parse(time[0]);
        this.deadlineTime = time[1];
    }

    /**
     * Returns the date in the form of a string of the format "MMM d yyyy".
     *
     * @return date
     */
    public String getDate() {
        return this.deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns a string representation of a deadline that has been marked.
     *
     * @return marked deadline
     */
    @Override
    public String markTask() {
        return String.format("[D] %s (by: %s %s)", this.task.markTask(),
                this.getDate(), this.deadlineTime);
    }

    /**
     * Returns a string representation of a deadline that has been unmarked.
     *
     * @return unmarked deadline
     */
    @Override
    public String unmarkTask() {
        return String.format("[D] %s (by: %s %s)", this.task.unmarkTask(),
                this.getDate(), this.deadlineTime);
    }

    /**
     * Returns a boolean value when comparing the deadline with the date string inputed.
     * Returns true if the dates are the same, and false otherwise.
     * Throws a KieTwoForOne exception when the users input an incorrect format for the date.
     *
     * @param date
     * @return Boolean
     * @throws KieTwoForOneException
     */
    @Override
    public boolean compareDate(String date) throws KieTwoForOneException {
        try {
            return this.deadlineDate.equals(LocalDate.parse(date));
        } catch (DateTimeParseException e) {
            throw new KieTwoForOneException("Date must be valid and in the form YYYY-MM-DD!");
        }
    }

    /**
     * Compares the details of the Deadline with the specified string input.
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
     * Returns the string representation of the Deadline object.
     *
     * @return String representation of the Deadline event.
     */
    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", this.task.toString(), this.getDate(), this.deadlineTime);
    }

}
