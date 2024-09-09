package rotodo.tasklist;

/**
 * The Todo class encapsulates the specific type of
 * Task, called ToDos. ToDos are tasks without any
 * date/time attached to it.
 *
 * @author Ng Kay Hian
 * @version CS2103T AY24/25 Semester 1
 */
public class Todo extends Task {
    /**
     * Initialise the Todo task.
     *
     * @param description of task
     * @param isDone status (can be true for loading data only)
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns task in saveString format.
     */
    public String saveString() {
        return "T | " + super.saveString();
    }
}
