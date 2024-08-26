import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.function.Function;

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

  public static Function<String, TaskLocalDate> createParserWithFrom(Function<?, TaskLocalDate> fromGenerator) {
    return (String dateString) -> {
      TaskLocalDate taskDate = TaskLocalDate.parse(dateString);
      TaskLocalDate from = fromGenerator.apply(null);
      if (taskDate.date.isBefore(from.date)) {
        throw new IllegalCommandException("Invalid date. End date " + taskDate + " is before " + from);
      }
      return taskDate;
    };
  }

  @Override
  public String toString() {
    return date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
  }
}
