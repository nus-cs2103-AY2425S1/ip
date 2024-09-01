public abstract class Task {
  private String task;
  private boolean isDone;

  public Task(String task) {
    this.task = task;
    this.isDone = false;
  }

  public abstract String toFileFormat();

  public String getStatus() {
    if (isDone) {
      return "[X]";
    }
    return "[ ]";
  }

  public void changeDoneStatus(boolean status) {
    this.isDone = status;
  }

  public Boolean getBooleanStatus() {
    return isDone;
  }

  @Override
  public String toString() {
    return getStatus() + " " + task; // [X] read book
  }
}