import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    public static Task parseTask(Task.TYPE taskType, Command command) throws InvalidTaskException {
        try {
            switch (taskType) {
                case TODO: {
                    try {
                        String description = command.getDescription();
                        return new ToDoTask(description, false);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new InvalidTaskException("OOPS!!! The description of a todo cannot be empty.");
                    }
                }
                case DEADLINE: {
                    try {
                        String description = command.getDescription();
                        String date = command.getDate();

                        return new DeadlineTask(description, false, date);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new InvalidTaskException("OOPS!!! The description or by of a deadline cannot be empty.");
                    }
                }
                case EVENT: {
                    try {
                        String description = command.getDescription();
                        String date = command.getDate();
                        String startTime = command.getStartTime();
                        String endTime = command.getEndTime();

                        return new EventTask(description, false, date, startTime, endTime);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new InvalidTaskException("OOPS!!! The description, date, start time or " +
                                "end time of an event cannot be empty.");
                    }
                }
                default:
                    throw new InvalidTaskException("OOPS!!! I'm sorry, but I don't what task is this. :-(");
            }
        } catch (InvalidDateException e) {
            throw new InvalidTaskException("OOPS!!! Please enter a valid " +
                    "date and time in the format yyyy-mm-dd.");
        }
        catch (InvalidTimeException e) {
            throw new InvalidTaskException("OOPS!!! Please enter a valid " +
                    "time in the format HHmm.");
        }
    }

    public static LocalDate parseDate(String date) throws InvalidDateException {
        try {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } catch (Exception e) {
            throw new InvalidDateException("Invalid date format. Please use the format dd-MM-yyyy.");
        }
    }

    public static String dateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    public static LocalTime parseTime(String time) throws InvalidTimeException {
        try {
            return LocalTime.parse(time, DateTimeFormatter.ofPattern("HHmm"));
        } catch (Exception e) {
            throw new InvalidTimeException("Invalid time format. Please use the format HHmm.");
        }
    }
}
