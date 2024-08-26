public class EventTask extends Task {
  private TaskLocalDate from;
  private TaskLocalDate to;

  public EventTask(String description, TaskLocalDate from, TaskLocalDate to) {
    super(description, TaskType.EVENT);
    this.from = from;
    this.to = to;
  }

  @Override
  public String toString() {
    return super.toString() + " (from: " + this.from + " to: " + this.to + ")";
  }
}