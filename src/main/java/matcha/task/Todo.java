package matcha.task;
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toSaveString() {
        return "T | " + super.toSaveString();
    }
    @Override
    public String toString() {
        return "\t[T]" + super.toString();
    }
}
