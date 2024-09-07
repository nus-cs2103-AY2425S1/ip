package echo.task;

/**
 * Represents a to-do task with descriptions
 *
 * @author Ernest Lim
 */
public class ToDo extends Task {

    /**
     * Constructor for ToDos object
     *
     * @param description description of the ToDos task
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the ToDos as a fancier string e.g. ToDo | 0 | work
     * Meant for recording in text files
     *
     * @return Fancier string of the ToDos
     */
    @Override
    public String toFancyString() {
        return "ToDo | " + super.getStatus() + " | "
                + super.description;
    }
}
