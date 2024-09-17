package talkabot.task;

/**
 * ToDo class contains task info.
 */
public class ToDo extends Task {

    /**
     * Constructor for ToDo class.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns string representation of todo.
     *
     * @return ToDo description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
