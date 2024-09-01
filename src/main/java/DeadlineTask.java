import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This is task with a deadline
 */
public class DeadlineTask extends Task{
    private LocalDate byDateObject;

    /**
     * Constructor for DeadlineTask
     *
     * @param description description of task
     * @param byDateObject  deadline of task
     */
    public DeadlineTask(String description, LocalDate byDateObject) {
        super(description);
        this.byDateObject = byDateObject;
    }

    /**
     * Constructor for DeadlineTask
     *
     * @param description description of task
     * @param byDateObject  deadline of task
     * @param isDone status of task
     */
    public DeadlineTask(String description, LocalDate byDateObject, boolean isDone) {
        super(description, isDone);
        this.byDateObject = byDateObject;
    }

    @Override
    public String saveString() {
        return "D | " + super.getStatusAndDescription() + " | " + byDateObject.toString();
    }

    /**
     * Overrides string representation to show more complete information of Deadline task
     *
     * @return string representation of Deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                byDateObject.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
