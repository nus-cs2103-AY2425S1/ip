package orion.tasks;

public class Todo extends Task {
    public Todo(String body) {
        super(body);
    }

    public Todo(String body, boolean isDone) {
        super(body, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String saveString() {
        return "todo," + super.saveString();
    }
}
