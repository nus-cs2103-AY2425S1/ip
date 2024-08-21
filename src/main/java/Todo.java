public class Todo extends Task {



    public Todo(String description) throws LightException {
        super(description);

    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}