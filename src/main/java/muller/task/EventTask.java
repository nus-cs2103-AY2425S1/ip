package muller.task;

import java.time.LocalDate;

/**
 * Represents a task that occurs over a range of dates.
 */
public class EventTask extends Task {
    private LocalDate startDate;
    private LocalDate endDate;

    /**
     * Constructs an EventTask object with the specified name, start date, and end date.
     *
     * @param name      The name of the event task.
     * @param startDate The start date of the event.
     * @param endDate   The end date of the event.
     */
    public EventTask(String name, LocalDate startDate, LocalDate endDate) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = "[E]";
    }

    /**
     * Checks if the task occurs on a specified date.
     *
     * @param date The date to check.
     * @return True if the date is within the event's start and end dates, false otherwise.
     */
    @Override
    public boolean isOnDate(LocalDate date) {
        return !date.isBefore(startDate) && !date.isAfter(endDate);
    }

    /**
     * Checks if the event starts within the next 3 days.
     *
     * @return True if the start date is within the next 3 days, false otherwise.
     */
    @Override
    public boolean isDueSoon() {
        return startDate.isBefore(LocalDate.now().plusDays(3)) && startDate.isAfter(LocalDate.now());
    }

    /**
     * Returns the start date of the task.
     *
     * @return The event start date.
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Returns the start date of the task.
     *
     * @return The event start date.
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Converts the EventTask to a string format suitable for saving to a file.
     *
     * @return The string representation of the EventTask for saving.
     */
    @Override
    public String convertToFileString() {
        return "E | " + (isDone() ? "1" : "0") + " | " + getName() + " | " + startDate.format(OUTPUT_DATE_FORMATTER)
                + " | " + endDate.format(OUTPUT_DATE_FORMATTER);
    }

    /**
     * Returns a string representation of the task, including its start and end dates.
     *
     * @return A string representing the task and its date range.
     */
    @Override
    public String toString() {
        return this.type + getDoneIcon() + " " + getName() + " (from: " + startDate.format(OUTPUT_DATE_FORMATTER)
                + " to: " + endDate.format(OUTPUT_DATE_FORMATTER) + ")";
    }
}

