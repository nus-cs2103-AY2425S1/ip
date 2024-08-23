public class Todo extends Task {
    public Todo (String name, int count) {
        super(name, count);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
