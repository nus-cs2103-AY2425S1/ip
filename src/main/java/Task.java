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

  public boolean getIsDone() {
    return isDone;
  }

  public void markAsDone() {
    isDone = true;
  }

  public void markAsUndone() {
    isDone = false;
  }
}
