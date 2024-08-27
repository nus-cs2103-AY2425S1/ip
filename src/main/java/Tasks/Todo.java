package tasks;

public class Todo extends Task {

    /**
     * Constructor for a Todo task.
     * @param description Description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * @return Returns the task icon, followed by its done/undone status.
     */
    @Override
    public String getStatusIcon() {
        return "[T]" + super.getStatusIcon();
    }
}
