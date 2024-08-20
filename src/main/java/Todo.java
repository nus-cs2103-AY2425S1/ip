public class Todo extends Task{

    Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return getTypeIcon() + super.toString();
    }

    @Override
    public String getTypeIcon() {
        return "[T]";
    }
}
