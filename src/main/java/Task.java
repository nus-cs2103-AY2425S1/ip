public class Task {
  private String task;
  private boolean isDone;

  public Task(String task) {
    this.task = task;
    this.isDone = false;
  }

  public String getStatus() {
    if (isDone) {
      return "[X]";
    }
    return "[ ]";
  }

  public void changeDoneStatus(boolean status) {
    this.isDone = status;
  }

  @Override
  public String toString() {
    return getStatus() + " " + task; // [X] read book
  }
}