public class Task {
  private String description;
  private boolean isDone;

  public Task(String description) {
    this.description = description;
    this.isDone = false;
  }

  public String getDescription() {
    return description;
  }

  public String getStatusIcon() {
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
}
