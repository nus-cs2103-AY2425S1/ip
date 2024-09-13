package pochat.tasks;

/**
 * This class represents a ToDo task that only contains a description
 *     without any time or deadline associated with it
 */
public class ToDo extends Task {
    /**
     * Constructor that takes in the necessary parameters to form the
     *     ToDo object
     * @param taskDescription description of the task
     * @param isDone representing whether the task is done or not
     */
    public ToDo(String taskDescription, boolean isDone) {
        super(taskDescription, isDone);
    }

    public ToDo(String taskDescription) {
        this(taskDescription, false);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof ToDo) {
            return ((ToDo) o).taskDescription.equals(taskDescription);
        }

        return false;
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
