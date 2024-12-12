package xizi.chatbot.task;

/**
 * Represents a task that needs to be done without any specific deadline.
 * This class extends the {@link Task} class and provides a simple task type.
 */
public class Todo extends Task {
    /**
     * Constructs a {@code Todo} task with the specified description.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }


    @Override
    public String toString() {
        return "[T]" + super.toString() + (this.getTags().isEmpty() ? "" : " [Tags: " + getTags() + "]");
    }

    /**
     * Returns the string representation of the {@code Todo} task in the format used for data file storage.
     * The format includes the task type, done status, and description.
     *
     * @return A string in the file format to save the task.
     */
    @Override
    public String toFileFormat() {
        String tags = this.getTags().isEmpty() ? "" : String.join(", ", this.getTags());
        return String.format("T | %d | %s | %s", this.isDone ? 1 : 0, this.name, tags);
    }
}
