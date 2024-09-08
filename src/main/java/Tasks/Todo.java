package Tasks;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[ToDo] " + super.toString());
    }
}
