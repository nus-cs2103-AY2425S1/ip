package pacman;

/**
 * Represents a todo task. A <code>Todo</code> object corresponds to
 * a task's name
 */
public class Todo extends Task {
    Todo(String task) {
        super(task);
    }

    /**
     * Return a <code>String</code> that is readable and writeable by <code>Storage</code>
     *
     * @return a <code>String</code> that is readable and writeable by <code>Storage</code>
     */
    public String toFile() { return "T/" + super.toFile(); }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
