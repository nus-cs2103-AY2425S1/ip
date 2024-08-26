public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
        Task.taskCount++;
    }

    @Override
    public String toDataString() {
        return "T | " + super.toDataString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
