package Dook.Tasks;

/**
 * Represents a Todo task, that has a description.
 */
public class Todo extends Task {

    /**
     * Creates a Todo with the specified description.
     *
     * @param description The description of the Todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a formatted string to be written to a file.
     *
     * @return The formatted string for file storage.
     */
    @Override
    public String fileFormatted() {
        return "T | " + super.fileFormatted();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Todo)) {
            return false;
        } else if (o == this) {
            return true;
        }

        Todo t = (Todo) o;

        return t.description.equals(this.description);
    }
}
