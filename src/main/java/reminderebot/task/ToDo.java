package reminderebot.task;

/**
 * ToDo represents a concrete Task with a description.
 */
public class ToDo extends Task {

    /**
     * Create a ToDo from the user input.
     */
    public ToDo(String description) {
        super(description);
    };

    /**
     * Creates an entry to a file from a ToDo
     * @return string representing the ToDo
     */
    @Override
    public String toFile() {
        return "T|" + getStatusIcon() + "|" + description;
    }

    /**
     * Create a ToDo from the text in text file.
     * @return ToDo
     */
    @Override
    public ToDo createFromFile(String file) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the String representing the ToDo to be displayed in tasklist.
     * @return string representing ToDo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
