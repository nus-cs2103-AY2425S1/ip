public class Todo extends Task {
    public Todo(String name) {
        this(name, false);
    }
    public Todo(String name, boolean isDone)  {
        super(name, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
