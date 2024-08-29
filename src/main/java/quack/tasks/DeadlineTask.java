package quack.tasks;

import java.time.LocalDateTime;

/**
 * This class defines and provides functionality for a deadline task.
 * <p>
 * A deadline task has an end date.
 */
public class DeadlineTask extends Task {

    /** When the deadline is due */
    private LocalDateTime dueDate;

    /**
     * Creates an deadline task object based on its description and due date.
     * @param description The description of the deadline.
     * @param dueDate When the deadline is due.
     */
    public DeadlineTask(String description, LocalDateTime dueDate) {

        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String toCSVFormat(){
        
        return "DEADLINE," + super.toCSVFormat() + "," + this.dueDate.format(Task.DATE_FORMAT) + "," + this.isChecked;
    }

    @Override
    public String toString() {

        return "[D]" + super.toString() + " (Due by: " + this.dueDate.format(Task.DATE_FORMAT) + ")";
    }
}
