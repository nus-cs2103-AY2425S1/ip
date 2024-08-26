public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSimplifiedString() {
        String formattedString = String.format("T | %d | %s\n", super.getIsDone() ? 1 : 0,
                super.getDescription());
        return formattedString;
    }
}
