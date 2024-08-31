package mummy.task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toFileRecord() {
        return String.format("T | %d | %s", this.isDone() ? 1 : 0, this.getDescription());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
