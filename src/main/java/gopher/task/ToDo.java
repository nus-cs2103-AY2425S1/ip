package gopher.task;

/**
 * Represents the todo Task.
 * Only includes task description.
 */
public class ToDo extends Task {
    /**
     * Constructor for todo class
     *
     * @param name name of the task
     */
    public ToDo(String name) {
        super(name);
    }

    @Override
    public void update(String[] tokens) {
        StringBuilder taskName = new StringBuilder();
        for (int i = 2; i < tokens.length; i++) {
            taskName.append(tokens[i]);
            if (i < tokens.length - 1) {
                taskName.append(" ");
            }
        }
        this.name = taskName.toString();
    }

    @Override
    public String getSaveMessage() {
        return String.format("T | %s | %s",
                getStatusIcon(),
                this.name);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
