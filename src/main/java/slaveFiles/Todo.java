package slaveFiles;

public class Todo extends Task {
    public Todo(String task) {
        super(task);
    }

    public Todo(boolean completed, String task) {
        super(completed, task);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
