public class Todo extends Task {
    protected String by;

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileFormat() {
        String status  = super.isDone ? "1|" : "0|";
        return "T|" + status + super.description;
    }
}
