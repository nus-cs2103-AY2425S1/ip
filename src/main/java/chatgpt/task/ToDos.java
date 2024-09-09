package chatgpt.task;

/**
 *  The ToDos class represents a simple task that only contains the task description/task name.
 */
public class ToDos extends Task {

    /**
     * Constructor of a new ToDo task.
     *
     * @param task is the description/task name
     */
    public ToDos(String task) {
        super(task);
    }

    /**
     * Constructor of a ToDo task that may have already been completed.
     *
     * @param task is the description/task name
     * @param isCompleted is the completion status of the task
     */
    public ToDos(String task, boolean isCompleted) {
        super(task, isCompleted);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toPrint() {
        return "T " + super.toPrint();
    }
}
