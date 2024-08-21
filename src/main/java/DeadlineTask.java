/**
* This class defines and prodides functionality for a deadline task.
* A deadline task has an end date.
*/
public class DeadlineTask extends Task {

    /** When the deadline is due */
    private String dueDate;

    /**
     * Constructor to create an event task object.
     * @param taskName The description of the deadline
     * @param dueDate When the deadline is due
     */
    DeadlineTask(String taskName, String dueDate) {

        super(taskName);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {

        return "[D]" + super.toString() + "(Due by: " + this.dueDate + ")";
    }
}
