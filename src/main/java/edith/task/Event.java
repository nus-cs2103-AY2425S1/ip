package edith.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents an Event task, which is a task that occurs during a specific time frame.
 * This class extends the abstract Task class.
 */
public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String taskStringToSave;

    /**
     * Constructs a new Event task with the specified description, start time, and end time.
     *
     * @param task             The description of the Event task.
     * @param startTimeString  The start time of the event as a string in the format "d/M/yyyy HHmm".
     * @param endTimeString    The end time of the event as a string in the format "d/M/yyyy HHmm".
     */
    public Event(String task, String startTimeString, String endTimeString) {
        super("[E] ", task);
        this.startTime = parseTime(startTimeString);
        this.endTime = parseTime(endTimeString);
        this.taskStringToSave = task + " /from " + startTimeString + " /to " + endTimeString;
    }

    /**
     * Parses a string representing a date and time into a LocalDateTime object.
     *
     * @param timeString The time string in the format "d/M/yyyy HHmm".
     * @return The parsed LocalDateTime object.
     */
    private LocalDateTime parseTime(String timeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return LocalDateTime.parse(timeString, formatter);
    }

    /**
     * Returns the start time of the event as a LocalDate object.
     *
     * @return The start time as a LocalDate.
     */
    public LocalDate getStartTime() {
        return this.startTime.toLocalDate();
    }

    /**
     * Returns the end time of the event as a LocalDate object.
     *
     * @return The end time as a LocalDate.
     */
    public LocalDate getEndTime() {
        return this.endTime.toLocalDate();
    }

    /**
     * Returns the string representation of the task to be saved to storage.
     *
     * @return The string to be saved.
     */
    @Override
    public String savedTaskString() {
        return taskStringToSave;
    }

    /**
     * Returns the string representation of the Event task.
     *
     * @return A string describing the Event task, including its status, description, start time, and end time.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, d MMM yyyy, ha", Locale.ENGLISH);
        String formattedStartTime = this.startTime.format(formatter);
        String formattedEndTime = this.endTime.format(formatter);
        String string = " (from: " + formattedStartTime + " - to: " + formattedEndTime + ")";
        return typeOfTaskString() + statusString() + taskString() + string;
    }
}
