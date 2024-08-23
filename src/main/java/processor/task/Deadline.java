package processor.task;

public class Deadline extends Task {
  private final String deadline;

  public Deadline(String name, String deadline) {
    super(name);
    this.deadline = deadline;
  }

  private Deadline(Deadline a, boolean state) {
    super(a, state);
    this.deadline = a.deadline;
  }

  @Override
  public Deadline mark() {
    return new Deadline(this, true);
  }

  @Override
  public Deadline unmark() {
    return new Deadline(this, false);
  }

  @Override
  public String getExtraInformation() {
    return "(by: " + this.deadline + ")";
  }

  @Override
  public String getSymbol() {
    return "[D]";
  }
}
