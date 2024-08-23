public class Task {
  private String description;
  private boolean isDone;
  private TaskType type;

  protected Task(String description, TaskType type) {
    this.description = description;
    this.isDone = false;
    this.type = type;
  }

  private String getStatusIcon() {
    return (this.isDone ? "X" : " "); // mark done task with X
  }

  public boolean markAsDone() {
    boolean success = !this.isDone;
    this.isDone = true;
    return success;
  }

  public boolean markAsUndone() {
    boolean success = this.isDone;
    this.isDone = false;
    return success;
  }

  @Override
  public String toString() {
    return "[" + TaskType.toChar(this.type) + "]" + "[" + this.getStatusIcon() + "] " + this.description;
  }
}
