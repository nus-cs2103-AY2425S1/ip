package alex.task;

/**
 * Represents an action to be done, with no time element included. A To-do object corresponds to
 * a piece of work represented by a string e.g., read book
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }
    /**
     * Returns a string that has the format to be shown to the user.
     *
     * @return string to be displayed to users.
     */
    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + super.toString();
    }
}
