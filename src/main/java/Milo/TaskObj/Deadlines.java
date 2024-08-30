package Milo.TaskObj;
import java.time.LocalDate;

/**
* Represents a deadline task, inheriting from task object, which contains
* an additional field deadline containing a date in LocalDate form
* and methods to display task in different string format
 */
public class Deadlines extends Task {
    private final LocalDate deadline;

    /*
    * initialise a deadline task object
    * task status set as not completed
    *
    * @param description of the deadline task
    * @param deadline date
     */

    public Deadlines(String desc, LocalDate deadline) {
        super(desc);
        this.deadline = deadline;
    }

    /*
     * initialise a deadline task object
     * task status may be set as completed
     *
     * @param description of the deadline task
     * @param deadline date
     * @param task completion status
     */
    public Deadlines(String desc, LocalDate deadline, Boolean isCompleted) {
        super(desc, isCompleted);
        this.deadline = deadline;
    }

    /*
     * overrides the task toString() method
     * provides additional task type and deadline information
     * format string for Ui
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }

    /*
     * overrides the task toTextString() method
     * provides additional task type and deadline information
     * format string for Storage
     */
    @Override
    public String toTextString() {
        return "D" + super.toTextString() + " | " + deadline;
    }
}
