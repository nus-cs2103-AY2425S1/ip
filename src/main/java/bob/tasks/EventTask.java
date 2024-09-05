package bob.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * EventTask is a child class of Task
 * EventTask is a task which has a from field and to field to indicate the duration of the event task
 */
public class EventTask extends Task {

    private final LocalDate startDate;
    private final LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;

    /**
     * Constructor for Event when only start date and end date is set
     *
     * @param taskName The name of the event
     * @param startDate The start date of the event
     * @param endDate The end date of the event
     */
    public EventTask(String taskName, LocalDate startDate, LocalDate endDate) {
        super(taskName);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Constructor for Event when only start date, end date and end time is set
     *
     * @param taskName The name of the event
     * @param startDate The start date of the event
     * @param endDate The end date of the event
     * @param endTime The end time of the event
     */
    public EventTask(String taskName, LocalDate startDate, LocalDate endDate, LocalTime endTime) {
        super(taskName);
        this.startDate = startDate;
        this.endDate = endDate;
        this.endTime = endTime;
    }

    /**
     * Constructor for Event when only start date, start time and end date is set
     *
     * @param taskName The name of the event
     * @param startDate The start date of the event
     * @param startTime The start time of the event
     * @param endDate The end date of the event
     */
    public EventTask(String taskName, LocalDate startDate, LocalTime startTime, LocalDate endDate) {
        super(taskName);
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
    }

    /**
     * Constructor for Event when start date, start time, end date and end time is set
     *
     * @param taskName The name of the event
     * @param startDate The start date of the event
     * @param startTime The start time of the event
     * @param endDate The end date of the event
     * @param endTime The end time of the event
     */
    public EventTask(String taskName, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        super(taskName);
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Exports the EventTask object to string to be saved in a text file
     *
     * @return String format of the EventTask object to be exported
     */
    @Override
    public String export() {
        return String.format("event %s /from %s%s /to %s%s", super.export(),
                this.startDate.format(DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                this.startTime != null ? " " + this.startTime.format(DateTimeFormatter.ofPattern("HHmm")) : "",
                this.endDate.format(DateTimeFormatter.ofPattern("dd/MM/uuuu")),
                this.endTime != null ? " " + this.endTime.format(DateTimeFormatter.ofPattern("HHmm")) : "");
    }

    /**
     * Converts the EventTask object to a string to be printed
     *
     * @return String format of the EventTask object for printing
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (from: %s%s to: %s%s)", "E", super.toString(),
                this.startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                this.startTime != null ? " " + this.startTime : "",
                this.endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                this.endTime != null ? " " + this.endTime : "");
    }
}
