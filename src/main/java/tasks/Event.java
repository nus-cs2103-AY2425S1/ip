package tasks;

public class Event extends Task {
  private String from;
  private String to;

  /**
   * Constructor for an Event
   * 
   * @param description
   * @param from
   * @param to
   */
  public Event(String description, String from, String to) {
    super(description);
    this.from = from;
    this.to = to;
  }

  public String toString() {
    return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
  }
}
