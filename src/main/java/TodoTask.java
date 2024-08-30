public class TodoTask extends Task{
    private String taskName;

    public TodoTask(String taskName) {
        super(taskName);
        this.taskName = taskName;
    }

    public TodoTask(String taskName, boolean isCompleted) {
        super(taskName, isCompleted);
        this.taskName = taskName;
    }

    @Override
    public String toFileFormat() {
        return "T | " + (super.isCompleted() ? "1" : "0") + " | "
                + taskName;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
