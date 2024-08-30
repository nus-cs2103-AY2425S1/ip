package kitty.tasks;

/**
 * Provides skeleton for a kind of tasks.Task named tasks.Todo.
 */
public class Todo extends Task{
    public Todo(String name) {
        super(name);
    }

    @Override
    public String getTaskData() {
        return String.format("T" + super.getTaskData() + "\n");
    }

    @Override
    public String toString() {
        return String.format("[T]" + super.toString());
    }
}
