import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

  private final LocalDate from;
  private final LocalDate to;

  public Event(String[] task) throws HamyoException {
    super(task);
    try {
      this.from = LocalDate.parse(task[1]);
      this.to = LocalDate.parse(task[2]);
    } catch (Exception e) {
      throw new HamyoException("Invalid date format. yyyy-mm-dd.");
    }
  }

  @Override
  public String toString() {
    return "[E] " + super.toString() + " (from: " + this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
  }

  @Override
  public String toFileFormat() {
    return "E" + " | " + super.toFileFormat() + " | " + this.from.toString() + " | " + this.to.toString();
  }
}
