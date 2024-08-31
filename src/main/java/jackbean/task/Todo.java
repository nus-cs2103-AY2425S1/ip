package jackbean.task;

/**
 * Represents a todo task in the JackBean chatbot.
 * This JavaDoc was written by GitHub Copilot.
 */
public class Todo extends Task {
    /**
     * Constructs a todo task with a description.
     * This JavaDoc was written by GitHub Copilot.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + description;
    }
}