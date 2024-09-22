package task;

/**
 * Task child class identical to task.
 */
public class ToDo extends Task {
    private static final String TASK_TYPE = "[T]";

    public ToDo(String task) {
        super(task);
    }

    @Override
    public String toStorageFormat() {
        return (TASK_TYPE + super.toString());
    }
    @Override
    public String toString() {
        return (TASK_TYPE + super.toString());
    }
}
