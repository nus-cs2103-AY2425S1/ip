package stelle.task;

/**
 * This class represents a stelle.task.ToDo.
 * It is child of the stelle.task.Task class.
 * @author Lee Ze Hao (A0276123J)
 */
public class ToDo extends Task {
    /**
     * Constructor for a stelle.task.ToDo object. Sets name upon creation.
     * @param name Name of stelle.task.ToDo object.
     */
    public ToDo(String name) {
        super(name, TaskType.TODO);
    }

    /**
     * Returns string representation of the stelle.task.ToDo.
     * @return String containing indication of stelle.task.ToDo class,
     *      and string representation of parent stelle.task.Task class.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
