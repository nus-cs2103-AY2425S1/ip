package Milo.TaskObj;
import java.time.LocalDate;

/**
 * Represents an event task, inheriting from task object, which contains
 * an additional field fromDate containing a date in LocalDate form
 * an additional field toDate containing a date in LocalDate form
 * and methods to display task in different string format
 */
public class Events extends Task {

    public final LocalDate fromDate;

    public final LocalDate toDate;

    /*
     * initialise an event task object
     * task status set as not completed
     *
     * @param description of the event
     * @param from date
     * @param to date
     */
    public Events(String desc, LocalDate from, LocalDate to) {
        super(desc);
        this.fromDate = from;
        this.toDate = to;
    }

    /*
     * initialise an event task object
     * task status may be set as completed
     *
     * @param description of the event
     * @param from date
     * @param to date
     */
    public Events(String desc, LocalDate from, LocalDate to, Boolean isCompleted) {
        super(desc, isCompleted);
        this.fromDate = from;
        this.toDate = to;
    }

    /*
     * overrides the task toString() method
     * provides additional task type, fromDate and toDate information
     * format string for Ui
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + fromDate + " to: " + toDate + ")";
    }

    /*
     * overrides the task toTextString() method
     * provides additional task type, fromDate and toDate information
     * format string for Storage
     */
    @Override
    public String toTextString() {
        return "D" + super.toTextString() + " | " + fromDate + " | " + toDate;
    }
}
