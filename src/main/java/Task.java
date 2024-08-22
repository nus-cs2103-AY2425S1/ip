public class Task {
  private String description;
  private boolean isDone;
  private char typeChar;

  protected Task(String description, char typeChar) {
    this.description = description;
    this.isDone = false;
    this.typeChar = typeChar;
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
    return "[" + this.typeChar + "]" + "[" + this.getStatusIcon() + "] " + this.description;
  }
}
