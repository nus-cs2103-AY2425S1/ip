package tars.tasks;

public class ToDo extends Task {

    public ToDo(String name) {
        super(name);
    }

    @Override
    public String saveTask() {
        return String.format("T|%s|%s", super.getStatus(), super.getTask());
    }
    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }
}
