public class Deadline extends Task {

  private final String deadline;

  public Deadline(String[] task) {
    super(task);
    this.deadline = task[1];
  }

  @Override
  public String toString() {
    return "[D] " + super.toString() + " (by: " + deadline + ")";
  }
}
