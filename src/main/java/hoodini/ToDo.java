package hoodini;
/**
 * Represents a ToDo task.
 */
public class ToDo extends Input{

    /**
     * Constructor for ToDo class.
     * @param input The input string
     */
    public ToDo(String input) {
        super(input.split(" ", 2)[1]);

    }

    /**
     * Returns the string representation of the ToDo task.
     * @return String representation of the ToDo task.
     */

    @Override
    public String toString() {
        String str = "[T] " + super.toString();
        return str;
    }
}
