import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {

  private final LocalDate deadlineDate;
  private final LocalDateTime deadlineDateTime;

  public Deadline(String[] task) throws HamyoException{
    super(task);
    try {
      LocalDateTime deadlineDateTimeTemp;
      try {
        deadlineDateTimeTemp = LocalDateTime.parse(task[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
      } catch (Exception e) {
        deadlineDateTimeTemp = null;
      } finally {
        this.deadlineDate = LocalDate.parse(task[1].split(" ")[0]);
      }
      this.deadlineDateTime = deadlineDateTimeTemp;
    } catch (Exception e) {
      throw new HamyoException("Invalid date/time format. yyyy-MM-dd OR yyyy-MM-dd HH:mm.");
    }
  }

  @Override
  public String toString() {
    String deadlineString = this.deadlineDateTime != null ?
        this.deadlineDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + "HRS":
        this.deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    return "[D] " + super.toString() + " (by: " + deadlineString + ")";
  }

  @Override
  public String toFileFormat() {
    String deadlineString = this.deadlineDateTime != null ?
        this.deadlineDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) :
        this.deadlineDate.toString();
    return "D" + " | " + super.toFileFormat() + " | " + deadlineString;
  }

  @Override public boolean occursToday(LocalDate date) {
    return date.isEqual(this.deadlineDate);
  }
}
