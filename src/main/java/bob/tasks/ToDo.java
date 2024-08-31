package bob.tasks;

public class ToDo extends Task{

    public ToDo(String taskName) {
        super(taskName);
    }

    @Override
    public String export() {
        return String.format("todo %s", super.export());
    }
    @Override
    public String toString() {
        return String.format("[%s]%s","T", super.toString());
    }
}
