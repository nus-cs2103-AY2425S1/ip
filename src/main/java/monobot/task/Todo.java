package monobot.task;

/**
 * Represents a Todo task.
 */
public class Todo extends Task {

    /**
     * Constructs a ToDo task with the specified description
     *
     * @param str The description of the ToDo task.
     */
    public Todo(String str) {
        super(str);
    }

    /**
     * Returns a string representation of the ToDo task.
     *
     * @return String representation of the ToDo task.
     */
    @Override
    public String toString() {
        String str = this.isDone ? "[T][X] " : "[T][ ] ";
        str += this.description.trim();
        return str;
    }
}
