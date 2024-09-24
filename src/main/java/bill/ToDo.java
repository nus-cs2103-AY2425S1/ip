package bill;

/**
 * The ToDo class extends the Task class, and allows creating tasks with a description.
 */
public class ToDo extends Task {
    /**
     * Initializes Todo.
     *
     * @param description Description of ToDo.
     */
    public ToDo(String description) {
        super(description);

        assert !description.isEmpty() : "All the todo descriptions should not be empty";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
