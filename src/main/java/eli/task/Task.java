package eli.task;

/**
 * Represents a task with a description and completion status.
 * This is an abstract class that serves as a base for specific task types like ToDo, Deadline, and Event.
 */
public abstract class Task {
  private String task;
  private boolean isDone;

  /**
   * Constructor for Task.
   *
   * @param task The description of the task.
   */
  public Task(String task) {
    this.task = task;
    this.isDone = false;
  }

  /**
   * Returns a string representation of the task formatted for file storage.
   *
   * @return The string representation of the task for file storage.
   */
  public abstract String toFileFormat();

  /**
   * Returns a string representation of the task status.
   *
   * @return The string representation of the task for task status.
   */
  public String getStatus() {
    if (isDone) {
      return "[X]";
    }
    return "[ ]";
  }

  public String getTask() {
    return this.task;
  }

  /**
   * Changes the done status of the task.
   *
   * @param status The new done status of the task.
   */
  public void changeDoneStatus(boolean status) {
    this.isDone = status;
  }

  /**
   * Returns the done boolean status of the task.
   */
  public Boolean getBooleanStatus() {
    return isDone;
  }

  @Override
  public String toString() {
    return getStatus() + " " + task;
  }

}