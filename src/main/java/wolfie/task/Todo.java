package wolfie.task;

/**
 * Represents a todos task.
 */
public class Todo extends Task {
    public Todo(String description, boolean isDone) {
        super(description, TaskType.TODO, isDone); // call the constructor of the parent class simple task
    }

    /**
     * Returns the task in the format to be saved in the file.
     * @return the task in the format to be saved in the file
     */
    @Override
    public String toFileFormat() {
        return "T | " + (getIsDone() ? "1" : "0") + " | " + getDescription();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Todo)) {
            return false;
        }
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
