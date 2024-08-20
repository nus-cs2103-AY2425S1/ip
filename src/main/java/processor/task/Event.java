package processor.task;

public class Event extends Task {
  private final String from;
  private final String to;

  public Event(String name, String from, String to) {
    super(name);
    this.from = from;
    this.to = to;
  }

  private Event(Event a, boolean state) {
    super(a, state);
    this.from = a.from;
    this.to = a.to;
  }

  @Override
  public Event mark() {
    return new Event(this, true);
  }

  @Override
  public Event unmark() {
    return new Event(this, false);
  }

  @Override
  public String getExtraInformation() {
    return "(from: " + from + ", to: " + to + ")";
  }

  @Override
  public String getSymbol() {
    return "[E]";
  }
}
