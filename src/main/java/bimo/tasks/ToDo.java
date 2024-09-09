package bimo.tasks;

/**
 * Creates a todo task with only description.
 */
public class ToDo extends Task {

    /**
     * Instantiates a todo task.
     *
     * @param details Description of task.
     */
    public ToDo(String details) {
        super(details);
    }

    /**
     * Converts task to string value with task type, description,
     *
     * @return String value of task.
     */
    @Override
    public String toString() {
        String todoString = "[T]" + super.toString();
        return todoString;
    }
}
