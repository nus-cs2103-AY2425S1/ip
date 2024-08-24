public class Event extends Task {

  private final String from;
  private final String to;

  public Event(String[] task) {
    super(task);
    this.from = task[1];
    this.to = task[2];
  }

  @Override
  public String toString() {
    return "[E] " + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
  }
}
