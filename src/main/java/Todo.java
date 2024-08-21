public class Todo extends Task {

    public Todo(String description, String from, String to) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}