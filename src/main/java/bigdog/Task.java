package bigdog;

public abstract class Task {

    /** The completion status of the task. */
    private boolean marked;

    /** The description of the task. */
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
     * Creates a Task instance from the given string representation.
     *
     * @param task the string representation of the task.
     * @return a Task object based on the provided string.
     * @throws BigdogException if the string does not start with a recognized task type.
     */
    public static Task of(String task) throws BigdogException {
        if (task.startsWith("todo")) {
            return Todo.of(task);
        } else if (task.startsWith("deadline")) {
            return Deadline.of(task);
        } else if (task.startsWith("event")) {
            return Event.of(task);
        } else {
            throw new BigdogException("Sorry the only keywords I recognise are \n1.todo\n2.deadline\n3.event" +
                    "\n4.mark\n5.unmark\n6.delete\n7.list\n8.bye ");
        }
    }

    /**
     * Creates a Task instance from the given string representation.
     *
     * @param task the string representation of the task.
     * @param marked the completion status of the task.
     * @return a Task object based on the provided string and completion status.
     * @throws BigdogException if the string does not start with a recognized task type.
     */
    public static Task of(String task, boolean marked) throws BigdogException {
        if (task.startsWith("T")) {
            return Todo.of(task, marked);
        } else if (task.startsWith("D")) {
            return Deadline.of(task, marked);
        } else if (task.startsWith("E")) {
            return Event.of(task, marked);
        } else {
            throw new BigdogException("Sorry the only keywords I recognise are \n1.todo\n2.deadline\n3.event" +
                    "\n4.mark\n5.unmark\n6.delete\n7.list\n8.bye ");
        }
    }

    /**
     * Returns the description of the task in string format.
     *
     * @return the description of the task.
     */
    public String getDescription() {
        return this.taskRep;
    }

    /**
     * Checks if the task is marked as completed.
     *
     * @return true if the task is marked, false otherwise.
     */
    public boolean isMarked() {
        return this.marked;
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        this.marked = true;
    }

    /**
     * Unmarks the task as not completed.
     */
    public void unmark() {
        this.marked = false;
    }

    /**
     * Returns a string representation of the task.
     * The string includes "[X]" if the task is marked as completed and "[ ]" if not.
     *
     * @return a string representation of the task.
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
