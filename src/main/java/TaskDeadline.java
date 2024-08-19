public class TaskDeadline extends Task {
    private String by;

    public TaskDeadline(String task, String by) {
        super(task);
        this.by = by;
  }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), by);
    }
}
