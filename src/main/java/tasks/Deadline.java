package tasks;

public class Deadline extends Task {
  private String by;

  /**
   * Constructor for a deadline task.
   * 
   * @param description The task description.
   * @param by          The due date of the task.
   */
  public Deadline(String description, String by) {
    super(description);
    this.by = by;
  }

  @Override
  public String toString() {
    return "[D]" + super.toString() + " (by: " + this.by + ")";
  }
}
