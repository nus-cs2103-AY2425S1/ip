/**
 * Represents a task of type "Deadline", which includes a description and a due date or time.
 * A Deadline task must be completed by a specified date or time.
 */
public class Deadline extends Task{
    protected String by; // The due date or time for the deadline

    /**
     * Constructs a Deadline task with the specified description and due date or time.
     *
     * @param description The description of the Deadline task.
     * @param by The due date or time for the task.
     */
    public Deadline(String description, String by) {
        super(description,TaskType.DEADLINE);
        this.by = by;
    }

    /**
     * Retrieves the due date or time of the deadline.
     *
     * @return The due date or time as a string.
     */
    public String getBy(){
        return this.by;
    }

    /**
     * Sets a new due date or time for the deadline.
     *
     * @param by The new due date or time as a string.
     */
    public void setBy(String by){
        this.by = by;
    }

    /**
     * Returns the type of this task, which is TaskType.DEADLINE.
     *
     * @return The TaskType of the task, specifically TaskType.DEADLINE.
     */
    @Override
    public TaskType type() {
        return TaskType.DEADLINE;
    }

    /**
     * Returns a string representation of the Deadline task.
     * The format includes the task type, status icon, description, and due date.
     *
     * @return A string in the format "[TaskType][StatusIcon] Description (by: due date)".
     */
    @Override
    public String toString(){
        return " ["+this.type()+"]["+this.getStatusIcon()+"] "+ this.getDescription()+" (by: "+this.by +")";
    }


}