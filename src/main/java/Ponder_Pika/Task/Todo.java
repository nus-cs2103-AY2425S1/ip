package Ponder_Pika.Task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String saveFullDetails() {
        return String.format("T | %b | %s", isDone(), getDescription());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
