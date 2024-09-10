package bigdog;

/**
 * The {@code Task} class represents a generic task in the application.
 * It provides methods for managing the task's description and its completion status.
 */
public abstract class Task {

    /** Error message for unrecognized task types. */
    private static final String UNRECOGNIZED_KEYWORDS_MESSAGE =
            "Sorry the only keywords I recognise are:\n"
                    + "1. todo\n"
                    + "2. deadline\n"
                    + "3. event\n"
                    + "4. mark\n"
                    + "5. unmark\n"
                    + "6. delete\n"
                    + "7. list\n"
                    + "8. bye";

    /** Indicates whether the task has been marked as done. */
    private boolean marked;

    /** The string representation of the task. */
    private String taskRep;

    /**
     * Constructs a Task with the specified description and completion status.
     *
     * @param taskRep the description of the task.
     * @param marked the completion status of the task.
     */
    public Task(String taskRep, boolean marked) {
        this.marked = marked;
        this.taskRep = taskRep;
    }

    /**
     * Factory method to create a Task object from a string representation.
     * Determines the type of task (Todo, Deadline, or Event) based on the string.
     *
     * @param task the string representation of the task.
     * @param marked the completion status of the task.
     * @return a Task object corresponding to the string representation.
     * @throws BigdogException if the task type is unrecognized or if the task string is null or empty.
     */
    public static Task of(String task, boolean marked) throws BigdogException {
        if (task == null || task.isEmpty()) {
            throw new BigdogException("Task cannot be empty!");
        }
        if (task.startsWith("T")) {
            return Todo.of(task, marked);
        } else if (task.startsWith("D")) {
            return Deadline.of(task, marked);
        } else if (task.startsWith("E")) {
            return Event.of(task, marked);
        } else {
            throw new BigdogException(UNRECOGNIZED_KEYWORDS_MESSAGE);
        }
    }

    /**
     * Returns the description of the task.
     *
     * @return the task description.
     */
    public String getDescription() {
        return this.taskRep;
    }

    /**
     * Returns whether the task is marked as done.
     *
     * @return {@code true} if the task is marked; {@code false} otherwise.
     */
    public boolean isMarked() {
        return this.marked;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.marked = true;
    }

    /**
     * Unmarks the task as not done.
     */
    public void unmark() {
        this.marked = false;
    }

    /**
     * Returns a string representation of the task.
     * The format includes the completion status and the task description.
     *
     * @return a formatted string representing the task.
     */
    @Override
    public String toString() {
        if (marked) {
            return "[X] " + taskRep;
        } else {
            return "[ ] " + taskRep;
        }
    }


}
