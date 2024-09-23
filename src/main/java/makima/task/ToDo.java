package makima.task;

/**
 * Task with no specified start or end date.
 */
public class ToDo extends Task {

    public final static int SAVE_PARAMETERS = 4;
    public ToDo(String name) {
        super(name);
    }

    public ToDo(String name, boolean done, PriorityLevel priorityLevel) {
        super(name, done, priorityLevel);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileString() {
        return String.format("T\n%s", super.toFileString());
    }
}
