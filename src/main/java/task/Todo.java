package task;

public class Todo extends Task {

    public Todo(String name) {
        super(name);
    }

    public Todo(String name, boolean isDone) {
        super(name, isDone);
    }

    @Override
    public String toFileString() {
        String done = this.isDone() ? "1" : "0";
        return "T," + done + "," + this.getName();
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
