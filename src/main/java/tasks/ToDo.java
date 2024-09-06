package tasks;

/**
 * ToDo class that extends Task class with no deadline or any date restrictions
 */
public class ToDo extends Task {

    /**
     * A constructor for ToDo class
     * @param description
     */
    public ToDo(String description) {
        super(description);
        Task.incrementTaskCount();
    }

    @Override
    public String toDataString() {
        return "T | " + super.toDataString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
