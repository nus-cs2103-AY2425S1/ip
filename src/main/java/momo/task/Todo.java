package momo.task;

public class Todo extends Task {
    public Todo(String task, boolean isCompleted) {
        super(task, isCompleted);
    }

    @Override
    public String toFileString() {
        return "T|" + super.toFileString();
    }

    public String toString() {
        return "[T]" + super.toString();
    }

}
