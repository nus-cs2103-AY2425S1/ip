package tecna.task;

/**
 * Represents the ToDo type of Task class.
 *
 * @author DennieDan.
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo task instance.
     *
     * @param taskName
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    /**
     * Constructs a ToDo task with <code>isDone</code>
     * mostly when parsing tasks from the storage file.
     *
     * @param taskName Name of the task.
     * @param isDone Done status of the task.
     */
    public ToDo(String taskName, boolean isDone) {
        super(taskName);
        if (isDone) {
            super.markAsDone();
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        ToDo toDo = (ToDo) obj;
        return taskName.equals(toDo.taskName);
    }

    @Override
    public int hashCode() {
        return taskName.hashCode();
    }
}
