package task;

/**
 * Represents a Deadline type Task with a due date.
 *
 * @author Youngseo Park (@youngseopark05)
 */
public class Deadline extends Task {
    private String dueDate;

    /**
     * Creates a Deadline task with the specified name and deadline.
     *
     * @param name The name of the deadline.
     * @param dueDate The due date of the deadline.
     */
    public Deadline(String name, String dueDate) {
        super(name);
        String[] dateParts = dueDate.split(" ");
        StringBuilder dateSB = new StringBuilder(dateParts[0]).append(" ");
        for (int i = 1; i < dateParts.length - 1; i++) {
            dateSB.append(dateParts[i]).append(" ");
        }
        if (dateParts.length > 1) {
            dateSB.append(dateParts[dateParts.length - 1]);
        }
        this.dueDate = dateSB.toString();
    }

    /**
     * Returns the String description of the task to append to /data/tasklist.txt.
     * Should be in this form: "D , {0 if not complete, 1 if complete} , {name} , {dueDate}".
     *
     * @return String description of task to append to /data/tasklist.txt.
     */
    @Override
    public String toFileString() {
        return "D , " + (isComplete() ? 1 : 0) + " , " + getName() + " , " + dueDate + "\n";
    }

    /**
     * Returns the String representation of the Deadline task as shown to the user on the HypeBot UI.
     * Should be in this form: "[D][{X only if complete}] {name} (by: {dueDate})".
     *
     * @return String representation of task as shown on HypeBot UI.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + dueDate + ")";
    }
}
