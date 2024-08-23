public class Task {
  protected String description;
  protected Boolean isDone;

  public Task(String description) {
      this.description = description;
      this.isDone = false;
  }

  public String getStatusIcon() {
      return (isDone ? "X" : " "); // mark done task with X
  }

  public void completeTask() {
      this.isDone = true;
  }

  public void uncompleteTask() {
      this.isDone = false;
  }

  public String toString() {
    String statusIcon = this.getStatusIcon();
    return "[" + statusIcon + "] " + this.description;
  }
}