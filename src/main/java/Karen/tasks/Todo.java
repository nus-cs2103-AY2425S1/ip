package Karen.tasks;

public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileString() {
        int markedInt = this.isMarked() ? 1 : 0;
        return String.format("T | %d | %s", markedInt, this.getName());
    }
}
