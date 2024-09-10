package tasks;

/**
 * A simple Todo class.
 */
public class Todo extends Task {
    /**
     * Creates an instance of Todo class
     *
     * @param description Is description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Creates an instance of Todo class
     *
     * @param line Is description.
     * @param completed Is a flag to indicate if complete.
     */
    public Todo(String line, boolean completed) {
        super(line, completed);
    }

    @Override
    public String getSaveFormat() {
        return String.format("T | %d | %s", super.intComplete(), super.getDescription());
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", completedStringRepresentation(), super.getDescription());
    }

    /**
     * Loads Todo object from storage.
     *
     * @param input Is the string representation of Todo in storage.
     * @return A Todo object.
     */
    public static Task load(String input) {
        assert !input.isEmpty();
        String[] parameters = input.split("\\|");
        assert parameters.length == 3;
        boolean completed = parameters[1].trim().equals("1");
        return new Todo(parameters[2].trim(), completed);
    }

}
