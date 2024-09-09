package matcha.task;

import java.time.LocalDateTime;

/**
 * Represents an event task with a start and end time.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an Event task given a description, start time and end time.
     *
     * @param description Description of the event.
     * @param from Start time of the event.
     * @param to End time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of the Event task when saving to a file.
     *
     * @return String representation of the Event task when saving to a file.
     */
    @Override
    public String toSaveString() {
        return "E | " + super.toSaveString() + " | " + this.from + " | " + this.to;
    }

    /**
     * Returns the string representation of the Event task when printing to the user.
     *
     * @return String representation of the Event task when printing to the user.
     */
    @Override
    public String toString() {
        return "\t[E]" + super.toString() + " (from: " + this.from.format(Task.getOutputFormat()) + " to: "
                + this.to.format(Task.getOutputFormat()) + ")";
    }
    /**
     * Checks if the Event is a duplicate of another Event.
     *
     * @param task The task to compare with.
     * @return Returns true if the task is a duplicate. Otherwise, returns false.
     */
    @Override
    public boolean checkDuplicate(Task task) {
        if (!(task instanceof Event)) {
            return false;
        }
        if (task == this) {
            return true;
        }
        //get the substring of the task description without the status icon
        String taskSubstring = task.toString().substring(this.getStatusIcon().length());
        String otherTaskSubstring = task.toString().substring(this.getStatusIcon().length());
        return taskSubstring.equals(otherTaskSubstring);
    }
}
