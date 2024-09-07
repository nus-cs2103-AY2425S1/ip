package nugget;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public String getTaskType() {
        return "[T]";
    }

    @Override
    public String toString() {
        return this.getTaskType() + super.toString();
    }
}
