package taskalyn;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event Task.
 */
public class EventTask extends Task {
    private final LocalDateTime fromDate;
    private final LocalDateTime toDate;

    /**
     * Constructs the EventTask object with description, from time, to time, and completion status.
     *
     * @param taskItem Description of Event Task.
     * @param fromDate From timing of event.
     * @param toDate To timing of event.
     * @param isCompleted Whether an Event Task is completed or not.
     */
    public EventTask(String taskItem, LocalDateTime fromDate, LocalDateTime toDate, boolean isCompleted) {
        super(taskItem, isCompleted);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    /**
     * Returns a String expression of the EventTask.
     *
     * @return String expression of EventTask
     */
    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MM yyyy, h:mm a");
        return "[E]" + super.toString() + " (from: " + this.fromDate.format(outputFormatter)
                + " to: " + this.toDate.format(outputFormatter) + ")";
    }

    /**
     * Returns a String expression used in database file.
     *
     * @return String expression used in database file.
     */
    @Override
    public String toDatabaseFormat() {
        DateTimeFormatter databaseFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        return "E | " + (this.isCompleted() ? "1" : "0") + " | "
                + this.getTaskDescription() + " | " + this.fromDate.format(databaseFormatter)
                + " | " + this.toDate.format(databaseFormatter);
    }
}
