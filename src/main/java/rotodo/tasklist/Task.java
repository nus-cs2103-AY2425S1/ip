package rotodo.tasklist;

/**
 * The Task class encapsulates a Task that has
 * a name, and has a 'done' state.
 *
 * @author Ng Kay Hian
 * @version CS2103T AY24/25 Semester 1
 */
public abstract class Task {
    private String value;
    private boolean done = false;

    /**
     * Initialse Task description and status.
     *
     * @param value description of task
     * @param status done status (for loading data only)
     */
    public Task(String value, boolean status) {
        this.value = value;
        this.done = status;
    }

    /**
     * Marks a task as done, and reports the task
     * current (new) 'done' state.
     *
     * @return task current state
     */
    public String markAsDone() {
        done = true;
        return "RoTodo is happy for you! Task done:\n  " + this.toString();
    }

    /**
     * Unmarks a task as done, and reports the task
     * current (new) 'done' state.
     *
     * @return task current state
     */
    public String unmarkAsDone() {
        done = false;
        return "Did something happen? RoTodo is confused... Task undone:\n  " + this.toString();
    }

    public boolean matchDescription(String keyword, boolean padding) {
        if (padding) {
            return value.toLowerCase().contains(" " + keyword.toLowerCase() + " ")
                || value.toLowerCase().startsWith(keyword.toLowerCase() + " ")
                || value.toLowerCase().endsWith(" " + keyword.toLowerCase())
                || value.toLowerCase().equals(keyword);
        } else {
            return value.toLowerCase().contains(keyword.toLowerCase());
        }
    }

    @Override
    public String toString() {
        return "[" + (done ? "X" : " ") + "] " + value;
    }

    public String saveString() {
        return (done ? "1" : "0") + " | " + value;
    }
}
