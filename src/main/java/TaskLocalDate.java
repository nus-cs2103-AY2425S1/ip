import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TaskLocalDate {
  private LocalDate date;

  private TaskLocalDate(LocalDate date) {
    this.date = date;
  }

  public static TaskLocalDate parse(String dateString) {
    try {
      LocalDate date = LocalDate.parse(dateString);
      return new TaskLocalDate(date);
    } catch (DateTimeParseException e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Override
  public String toString() {
    return date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
  }
}
