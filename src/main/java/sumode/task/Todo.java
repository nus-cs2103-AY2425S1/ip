package sumode.task;

/**
 * A class for various tasks without time constraints.
 */
public class Todo extends Task {

    /**
     * Constructor for Todo
     *
     * @param name Name of task.
     */
    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a String in the format to be stored in data file.
     * @return a String in the format to be stored in data file.
     */
    @Override
    public String savedString() {
        return "T | " + super.savedString();
    }
}
