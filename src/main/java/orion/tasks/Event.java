package orion.tasks;

import orion.exceptions.OrionException;
import orion.exceptions.OrionInputException;

import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * Represents an event with a start and end date.
 * Extends the {@link Task} class to include start and end dates.
 */
public class Event extends Task {
    private LocalDate start;
    private LocalDate end;

    /**
     * Constructs an {@code Event} with the specified body, start date, and end date.
     * The task is initialized as not done.
     *
     * @param body the description of the task
     * @param start the start date of the event as a {@code LocalDate}
     * @param end the end date of the event as a {@code LocalDate}
     */
    public Event(String body, LocalDate start, LocalDate end) {
        super(body);
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs an {@code Event} with the specified body, start date string, and end date string.
     * The task is initialized as not done.
     *
     * @param body the description of the task
     * @param from the start date of the event as a string in the format "yyyy-mm-dd"
     * @param to the end date of the event as a string in the format "yyyy-mm-dd"
     * @throws OrionException if the date strings cannot be parsed into {@code LocalDate} or if the start date is after the end date
     */
    public Event(String body, String from, String to) throws OrionException {
        super(body);
        try {
            LocalDate start = LocalDate.parse(from);
            LocalDate end = LocalDate.parse(to);
            if (start.isAfter(end)) {
                throw new OrionInputException("Your start date must be earlier than your end date!");
            }
            this.start = start;
            this.end = end;
        } catch (DateTimeException e) {
            throw new OrionInputException("Correct syntax: event <task> /from <yyyy-mm-dd> /to <yyyy-mm-dd>. " +
                    "Please input valid dates in the correct format!");
        }
    }

    /**
     * Constructs an {@code Event} with the specified body, completion status, start date, and end date.
     *
     * @param body the description of the task
     * @param isDone the completion status of the task
     * @param start the start date of the event as a {@code LocalDate}
     * @param end the end date of the event as a {@code LocalDate}
     */
    public Event(String body, boolean isDone, LocalDate start, LocalDate end) {
        super(body, isDone);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a string representation of a date in the format "day/month/year".
     *
     * @param date the date to be formatted
     * @return the string representation of the date
     */
    private String getTimeString(LocalDate date) {
        return date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear();
    }

    /**
     * Returns a string representation of the {@code Event}.
     * The format is "[E][task] (from: day/month/year, to: day/month/year)".
     *
     * @return the string representation of the {@code Event}
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s, to: %s)", super.toString(), getTimeString(this.start), getTimeString(this.end));
    }

    /**
     * Returns a string representation of the {@code Event} for saving to a file.
     * The format is "event,[task],[isDone],[start date],[end date]".
     *
     * @return the string representation of the {@code Event} for saving
     */
    @Override
    public String saveString() {
        return "event," + super.saveString() + "," + this.start + "," + this.end;
    }
}
