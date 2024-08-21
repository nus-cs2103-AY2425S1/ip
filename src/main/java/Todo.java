public class Todo extends Task{

    public Todo(String description) throws IllegalArgumentException {
        super(description);
        if (description.isEmpty()) {
            throw new IllegalArgumentException("The description of a todo cannot be empty!");
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
