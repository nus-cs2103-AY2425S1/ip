package processor.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
  private final LocalDateTime deadline;

  public Deadline(String name, LocalDateTime deadline) {
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
    return "(by: " + this.deadline.format(DateTimeFormatter.ofPattern("E, MMM d yyyy HH:mm:ss")) + ")";
  }

  @Override
  public String getSymbol() {
    return "[D]";
  }
}
