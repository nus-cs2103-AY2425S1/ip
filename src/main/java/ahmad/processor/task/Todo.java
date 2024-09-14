package ahmad.processor.task;

/**
 * Todo class that extends from Task.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo instance based on the name.
     *
     * @param name Name of todo.
     */
    public Todo(String name) {
        super(name);
    }

    private Todo(Todo a, boolean state) {
        super(a, state);
    }

    @Override
    protected long getTime() {
        return -1;
    }

    @Override
    public Todo mark() {
        return new Todo(this, true);
    }

    @Override
    public Todo unmark() {
        return new Todo(this, false);
    }

    @Override
    public String getExtraInformation() {
        return "";
    }

    @Override
    public String getSymbol() {
        return "[T]";
    }
}
