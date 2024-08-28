package deez;

public class Todo extends Task {

    private static final long serialVersionUID = 1L;

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
