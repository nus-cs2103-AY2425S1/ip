package derek.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The {@code EventTask} class represents a task that occurs during a specific time frame.
 * It extends the {@code Task} class and includes a start time and an end time.
 */
public class EventTask extends Task {
    private String startTime;
    private String endTime;

    /**
     * Constructs an {@code EventTask} object with the specified name, start time, and end time.
     * The start and end times are parsed and formatted to include both date and time.
     *
     * @param name the name or description of the event task
     * @param startTime the start time of the event in the format "dd/MM/yyyy HH:mm"
     * @param endTime the end time of the event in the format "dd/MM/yyyy HH:mm"
     * @throws DateTimeParseException if the start or end time cannot be parsed into the expected format
     */
    public EventTask(String name, String startTime, String endTime) throws DateTimeParseException {
        super(name);

        // Use LocalDateTime instead of LocalDate to handle date and time
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime inputStartTime = LocalDateTime.parse(startTime.trim(), inputFormatter);
        LocalDateTime inputEndTime = LocalDateTime.parse(endTime.trim(), inputFormatter);

        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
        this.startTime = inputStartTime.format(outputFormatter);
        this.endTime = inputEndTime.format(outputFormatter);
    }


    /**
     * Constructs an {@code EventTask} object with the specified name, start time, end time, and completion status.
     *
     * @param name the name or description of the event
     * @param startTime the start time of the event
     * @param endTime the end time of the event
     * @param isCompleted the completion status of the task (e.g., "X" for completed)
     */
    public EventTask(String name, String startTime, String endTime, String isCompleted) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
        if (isCompleted.equals("X")) {
            super.markCompleted();
        }
    }


    /**
     * Gets the start time of the event.
     *
     * @return the start time as a formatted string
     */
    public String getStartTime() {
        return this.startTime;
    }

    /**
     * Gets the end time of the event.
     *
     * @return the end time as a formatted string
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * Gets the name of the event.
     *
     * @return the name of the event
     */
    public String getName() {
        return super.getName();
    }

    /**
     * Returns a string representation of the event task, including the task name,
     * start time, and end time, formatted as "(from: startTime to: endTime)".
     *
     * @return a formatted string representation of the event task
     */
    @Override
    public String toString() {
        return String.format("[E]"
                + super.toString()
                + " (from: "
                + this.startTime
                + " to: "
                + this.endTime
                + ")");
    }
}
