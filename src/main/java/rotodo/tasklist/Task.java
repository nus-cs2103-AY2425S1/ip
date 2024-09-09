package rotodo.tasklist;

/**
 * The Task class encapsulates a Task that has
 * a name, and has a 'done' state.
 *
 * @author Ng Kay Hian
 * @version CS2103T AY24/25 Semester 1
 */
public abstract class Task {
    private String description;
    private boolean isDone = false;

    /**
     * Initialse Task description and status.
     *
     * @param description of task
     * @param isDone status (can be true for loading data only)
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks a task as done, and reports the task
     * current (new) 'done' state.
     *
     * @return task current state
     */
    public String markAsDone() {
        isDone = true;
        return "RoTodo is happy for you! Task done:\n  " + this.toString();
    }

    /**
     * Unmarks a task as done, and reports the task
     * current (new) 'done' state.
     *
     * @return task current state
     */
    public String unmarkAsDone() {
        isDone = false;
        return "Did something happen? RoTodo is confused... Task undone:\n  " + this.toString();
    }

    /**
     * Checks if task match given keyword (with/without padding).
     *
     * @param keyword
     * @param padding indicate to match with padding
     * @return true if match, else false
     */
    public boolean matchDescription(String keyword, boolean padding) {
        boolean containKeyword = description.toLowerCase().equals(keyword.toLowerCase());
        boolean containKeywordPadded = description.toLowerCase().contains(" " + keyword.toLowerCase() + " ");
        boolean containKeywordTrailing = description.toLowerCase().startsWith(keyword.toLowerCase() + " ");
        boolean containKeywordLeading = description.toLowerCase().endsWith(" " + keyword.toLowerCase());
        if (padding) {
            return containKeywordPadded || containKeywordTrailing || containKeywordLeading || containKeyword;
        } else {
            return containKeyword;
        }
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }

    public String saveString() {
        return (isDone ? "1" : "0") + " | " + description;
    }
}
