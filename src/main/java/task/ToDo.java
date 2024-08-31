package task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public ToDo setAsDone() {
        return new ToDo(this.getDescription(), true);
    }

    @Override
    public ToDo setAsUndone() {
        return new ToDo(this.getDescription(), false);
    }

    @Override
    public ToDo setDescription(String description) {
        return new ToDo(description, this.isDone());
    }

    @Override
    public String toFileRecord() {
        return String.format("T | %s", this.getDescription());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
