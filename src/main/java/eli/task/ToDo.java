package eli.task;

/**
 * Represents a ToDo task without any specific date.
 */
public class ToDo extends Task {

  /**
   * Constructor for ToDo.
   *
   * @param task The description of the ToDo task.
   */
  public ToDo (String task) {
    super(task);
  }

  @Override
  public String toFileFormat() {
    return "T | " + (this.getBooleanStatus() ? "1" : "0") + " | " + this.getTask();
  }

  @Override
  public String toString() {
    return "[T] " + super.toString();
  }
}