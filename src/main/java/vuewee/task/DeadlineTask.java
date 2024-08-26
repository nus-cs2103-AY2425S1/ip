package vuewee.task;

public class DeadlineTask extends Task {
  private TaskLocalDate by;

  public DeadlineTask(String description, TaskLocalDate by) {
    super(description, TaskType.DEADLINE);
    this.by = by;
  }

  @Override
  public String toString() {
    return super.toString() + " (by: " + this.by.toString() + ")";
  }
}
