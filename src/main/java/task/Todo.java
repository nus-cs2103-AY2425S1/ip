package task;

public class Todo extends Task{

    public Todo(String title) {
        this(title, false);
    }

    public Todo(String title, boolean isDone) {
        super(title, isDone);
    }

    @Override
    public String storageFormat() {
        return String.format("TODO | %s | %s", super.getStatus(), super.getTitle());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
