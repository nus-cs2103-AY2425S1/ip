package eli.task;

/**
 * Represents an event task that starts and ends at specific times.
 */
public class Event extends Task {

  private String from;
  private String to;

  /**
   * Constructor for Event.
   *
   * @param task The description of the event task.
   * @param from        The start time of the event.
   * @param to          The end time of the event.
   */
  public Event(String task, String from, String to) {
    super(task);
    this.from = from;
    this.to = to;
  }

  @Override
  public String toFileFormat() {
    return "E | " + (this.getBooleanStatus() ? "1" : "0") + " | " + this + " | " + from + " to " + to;
  }

  @Override
  public String toString() {
    return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
  }
}
