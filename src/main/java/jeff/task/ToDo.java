package jeff.task;

/**
 * Represents a To Do task with a description.
 *
 * A To Do object stores information about a task that needs to be done without any specific deadline.
 */
public class ToDo extends Task {

    /**
     * Constructs a To Do object with the specified description.
     *
     * @param task Description of the task.
     */
    public ToDo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String saveAsCSV() {
        return "T," + super.saveAsCSV();
    }
}
