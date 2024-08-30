package atlas.tasks;

/**
 * Represents a todo class containing the methods to create a todo and represent it as strings.
 */
public class Todo extends Task {
    /**
     * Creates a Todo object.
     *
     * @param name The name or description of the todo.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * @return String The format in which this todo is stored in a file.
     */
    @Override
    public String toFileString() {
        return "T " + super.toFileString();
    }

    /**
     * @return String The format in which this todo is shown in the ui.
     */
    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }
}
