package tasks;

/**
 * ToDo represents a specific subset of Task that comes with an additional deadline
 */
public class ToDo extends Task {

    /**
     * A constructor for ToDo class
     * @param description the description of the task
     * @param priority the priority of the task
     */
    public ToDo(String description, int priority) {
        super(description, priority);
        Task.incrementTaskCount();
    }

    @Override
    public String toDataString() {
        return "T | " + super.toDataString() + " | " + super.getPriorityNum();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
