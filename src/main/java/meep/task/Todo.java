package meep.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getSaveFormat() {
        return "T|" + super.getSaveFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
