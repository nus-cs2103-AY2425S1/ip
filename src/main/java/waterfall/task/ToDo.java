package waterfall.task;

/**
 * Represents a specific <code>Task</code> type known as <code>Todo</code>.
 * The <code>Todo</code> object consists of a title only.
 *
 * @author Wai Hong
 */

public class ToDo extends Task {

    /**
     * Constructs a Todo object with the specified title.
     * @param title Title of the task
     */
    public ToDo(String title) {
        super(title);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toStorageString() {
        if (this.isDone()) {
            return "T | 1 | " + this.getTitle();
        } else {
            return "T | 0 | " + this.getTitle();
        }
    }
}
