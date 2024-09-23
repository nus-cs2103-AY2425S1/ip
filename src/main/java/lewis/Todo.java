package lewis;

/**
 * This class implements a todo,
 * which can capture a description of this todo,
 * and its completion status
 */
public class Todo extends Task {
    /**
     * Protected constructor for a todo.
     * This should be used to create a new Todo.
     * By default, its completion status is NOT_DONE
     * @param description a description of this todo
     */
    protected Todo(String description) {
        super(description);
    }

    /**
     * Protected constructor for a todo.
     * This should be used to load a todo from storage.
     * @param description a string representation of this todo
     * @param status a string representation of this todo's completion status
     */
    protected Todo(String description, String status) {
        super(description, Status.valueOf(status));
    }

    /**
     * Returns a string representation of this todo
     * @return a string
     */
    @Override
    public String toString() {
        return "[T]"
                + super.toString();
    }

    /**
     * Returns a csv representation of this todo.
     * @return A string in the form "Todo,(task description),(task status)"
     */
    @Override
    protected String toCsv() {
        return "Todo,"
                + super.toCsv();
    }

    /**
     * Overrides the default comparable logic of a task.
     * A todo has an arbitary deadline and should be brought to the highest priority
     * so that the user doesn't bury it.
     * @param task the task to be compared.
     * @return -1 if this has a higher priority
     *          0 if the two tasks have equal priority
     *          1 if this task has a lower priority
     */
    @Override
    public int compareTo(Task task) {
        //If the two tasks have different statuses
        if (this.status != task.status) {
            if (this.status == Status.DONE) {
                return 1;
            } else {
                return -1;
            }
        }
        //If the two tasks have the same status
        if (task instanceof Todo) {
            //If both are todos, then they should be sorted lexicographically
            return this.toString().compareTo(task.toString());
        } else {
            //A todo should take precedence over a deadline or an event
            return -1;
        }
    }
}
