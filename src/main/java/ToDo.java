public class ToDo extends Task{
    public ToDo(String description) {
        super(description);
    }

    public ToDo(boolean isDone, String task) {
        super(isDone, task);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
