public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
        Task.taskCount++;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
