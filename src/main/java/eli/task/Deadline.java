package eli.task;
/**
 * Represents a Deadline task with a specific deadline.
 */
public class Deadline extends Task {

  // deadline return book /by Sunday
  private String by;

  /**
   * Constructor for Deadline.
   *
   * @param task The description of the ToDo task.
   * @param by The deadline of the ToDo task.
   */
  public Deadline(String task, String by) {
    super(task);
    this.by = by;
  }
  @Override
  public String toFileFormat() {
    return "D | " + (this.getBooleanStatus() ? "1" : "0") + " | " + this + " | " + by;
  }

  @Override
  public String toString() {
    return "[D]" + super.toString() + " (by: " + by + ")";
  }
}