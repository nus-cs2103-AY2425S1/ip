package util;

public class Task {
  private String description;
  private boolean isDone;

  /**
   * Constructor for a Task.
   * 
   * @param description
   */
  public Task(String description) {
    this.description = description;
    this.isDone = false;
  }

  /**
   * Method to mark the task as done.
   */
  public void markDone() {
    this.isDone = true;
  }

  /**
   * Methd to mark the task as undone.
   */
  public void markUndone() {
    this.isDone = false;
  }

  /**
   * Method to check the status of the task.
   * 
   * @return true if the task is done else false.
   */
  public boolean isDone() {
    return this.isDone;
  }

  @Override
  public String toString() {
    return (this.isDone ? "[x] " : "[ ] ") + this.description;
  }
}
