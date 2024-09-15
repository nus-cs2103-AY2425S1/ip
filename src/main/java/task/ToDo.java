package task;

/**
 * A todo task.
 *
 * @author IsaacPangTH
 */
public class ToDo extends Task {

    /**
     * Constructor for a <code>ToDo</code>.
     *
     * @param description Description of the task.
     */
    public ToDo(String description, boolean isCompleted) {
        super(description, isCompleted);
    }

    /**
     * Constructor for an incomplete <code>ToDo</code>.
     *
     * @param description Description of the task.
     */
    public ToDo(String description) {
        this(description, false);
    }

    /**
     * Factory method which creates a <code>ToDo</code> from a string containing data of the todo.
     *
     * @param data Data of the ToDo in form "completion status|description".
     * @return <code>ToDo</code> from data.
     */
    public static ToDo of(String data) {
        String[] args = data.split("\\|");

        boolean isCompleted = Boolean.parseBoolean(args[0]);
        String description = args[1];

        return new ToDo(description, isCompleted);
    }

    @Override
    public String getTypeIcon() {
        return "T";
    }
}
