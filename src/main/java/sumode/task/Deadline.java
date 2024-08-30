package sumode.task;

/**
 * A class for various tasks with deadlines
 */
public class Deadline extends Task {

    private final DueFromToFormat due;

    /**
     * Constructor for Deadline
     *
     * @param name Name of task.
     * @param due Due date of task.
     */
    public Deadline(String name, String due) {
        super(name);
        this.due = new DueFromToFormat(due);
    }

    @Override
    public String toString() {
        return "[D]"
                + super.toString()
                + " (by: "
                + due
                + ")";
    }

    /**
     * Returns a String in the format to be stored in data file.
     * @return a String in the format to be stored in data file.
     */
    @Override
    public String savedString() {
        return "D | " + super.savedString() + " | " + this.due;
    }
}
