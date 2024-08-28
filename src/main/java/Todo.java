public class Todo extends Task {
    public Todo (String description, boolean isMarked) {
        super(description, isMarked);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
