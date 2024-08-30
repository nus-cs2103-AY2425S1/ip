package jackson.tasks;

/**
 * Todo class containing name.
 */
public class Todo extends Task {

    /**
     * Constructs Todo task instance
     * @param name String name of the task.
     */
    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
