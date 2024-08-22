/**
 * Represents a task of type "Event", which includes a description, start time, and end time.
 * An Event task tracks a specific time frame in addition to the task's description and status.
 */
public class Event extends Task{
    private String to; // The start time or date of the event
    private String from; // The end time or date of the event

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     *
     * @param description The description of the Event task.
     * @param from The start time or date of the event.
     * @param to The end time or date of the event.
     */
    public Event(String description, String from, String to){
        super(description,TaskType.EVENT);
        this.to = to;
        this.from = from;
    }

    /**
     * Retrieves the start time or date of the event.
     *
     * @return The start time or date of the event as a string.
     */
    public String getFrom(){
        return this.from;
    }

    /**
     * Sets the start time or date of the event.
     *
     * @param from The new start time or date of the event.
     */
    public void setForm(String from){
        this.from = from;
    }

    /**
     * Returns the type of this task, which is TaskType.EVENT.
     *
     * @return The TaskType of the task, specifically TaskType.EVENT.
     */
    @Override
    public TaskType type() {
        return TaskType.EVENT;
    }

    /**
     * Returns a string representation of the Event task.
     * The format includes the task type, status icon, description, start time, and end time.
     *
     * @return A string in the format "[TaskType][StatusIcon] Description (from: start to: end)".
     */
    @Override
    public String toString(){
        return " ["+this.type()+"]["+this.getStatusIcon()+"] "+ this.getDescription()+" (from: "+this.from + " to: " + this.to+ ")";
    }
}
