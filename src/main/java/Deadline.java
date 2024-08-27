import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {

  private final LocalDate deadline;

  public Deadline(String[] task) throws HamyoException{
    super(task);
    try {
      this.deadline = LocalDate.parse(task[1]);
    } catch (Exception e) {
      throw new HamyoException("Invalid date format. yyyy-mm-dd.");
    }
  }

  @Override
  public String toString() {
    return "[D] " + super.toString() + " (by: " + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
  }

  @Override
  public String toFileFormat() {
    return "D" + " | " + super.toFileFormat() + " | " + this.deadline.toString();
  }
}
