package bibi.task;

public class ToDo extends Task {

    public ToDo(String task) {
        // Call bibi.task.Task constructor
        super(task);
    }

    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }
}
