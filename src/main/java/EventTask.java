import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * These are tasks with specific start and end date time
 */
public class EventTask extends Task {

    private LocalDate startDateObject;
    private LocalDate endDateTime;
    /**
     * Constructur for Event task
     *
     * @param description description of task
     * @param startDateObject start date of task
     * @param endDateTime end date of task
     */
    public EventTask(String description, LocalDate startDateObject, LocalDate endDateTime) {
        super(description);
        this.startDateObject = startDateObject;
        this.endDateTime = endDateTime;
    }

    /**
     * Constructur for Event task
     *
     * @param description description of task
     * @param startDateObject start date of task
     * @param endDateTime end date of task
     * @param isDone status of task
     */
    public EventTask(String description, LocalDate startDateObject,
                     LocalDate endDateTime, boolean isDone) {
        super(description, isDone);
        this.startDateObject = startDateObject;
        this.endDateTime = endDateTime;
    }

    @Override
    public String saveString() {
        return "E | " + super.getStatusAndDescription() + " | " +
                startDateObject.toString() + " | " + endDateTime.toString();
    }

    /**
     * Overrides string representation to show more complete information of Event task
     *
     * @return string representation of Event task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " +
                this.startDateObject.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " +
                this.endDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

}
