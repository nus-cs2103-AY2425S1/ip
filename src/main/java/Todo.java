public class Todo extends Task {
    public Todo(String body) {
        super(body);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
