package Milo.TaskObj;

/**
 * Represents a to-do task, inheriting from task object, which contains
 * methods to display task in different string format
 */
public class Todos extends Task {

    /*
     * initialise a deadline task object
     * task status set as not completed
     *
     * @param description of the to-do task
     */
    public Todos(String desc) {
        super(desc);
    }

    /*
     * initialise a deadline task object
     * task status may be set as completed
     *
     * @param description of the to-do task
     */
    public Todos(String desc, Boolean isCompleted) {
        super(desc, isCompleted);
    }

    /*
     * overrides the task toString() method
     * provides additional task type information
     * format string for Ui
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /*
     * overrides the task toTextString() method
     * provides additional task type information
     * format string for Storage
     */
    @Override
    public String toTextString() {
        return "T" + super.toTextString();
    }
}
