public class TaskDeadline extends Task {
    private String by;

    public TaskDeadline(String task, String by) {
        super(task);
        this.by = by;
  }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by);
    }

    @Override
    public String toSaveString() {
        return String.format("D|%d|%s|%s", isDone() ? 1 : 0, getTask(), by);
    }
}
