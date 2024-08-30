package jarvis;

/**
 * Represents a Todo task.
 * This class extends the {@link Task} class and includes a name and completion status.
 */
public class Todo extends Task {

    /**
     * Constructs a {@code Todo} object with the specified name.
     *
     * @param name the name of the Todo task.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Returns a string representation of the Todo task.
     * The format includes the task type "[T]", the completion status checkbox "[ ]" or "[X]",
     * and the task name.
     *
     * @return a string representation of the Todo task.
     */
    @Override
    public String toString() {
        String checkbox = this.isDone() ? "[X]" : "[ ]";
        String result = String.format(" %s %s\n", checkbox, this.getName());
        return "[T]" + result;
    }
}