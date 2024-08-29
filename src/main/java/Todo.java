/**
 * Provides skeleton for a kind of Task named Todo.
 */
public class Todo extends Task{
    public Todo(String name) {
        super(name);
    }

    @Override
    public String taskData() {
        return String.format("T" + super.taskData() + "\n");
    }

    @Override
    public String toString() {
        return String.format("[T]" + super.toString());
    }
}
