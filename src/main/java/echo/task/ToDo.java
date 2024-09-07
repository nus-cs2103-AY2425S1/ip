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
     * @param toDoArray array of string with the format [description]
     */
    public ToDo(String[] toDoArray) {
        super(toDoArray);
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
