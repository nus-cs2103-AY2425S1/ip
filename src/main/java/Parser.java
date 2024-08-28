import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    public static Task parseTask(Task.TYPE taskType, String command) throws InvalidTaskException {
        switch (taskType) {
            case TODO: {
                try {
                    String description = command.split(" ", 2)[1].trim();
                    return new ToDoTask(description, false);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new InvalidTaskException("OOPS!!! The description of a todo cannot be empty.");
                }
            }
            case DEADLINE: {
                try {
                    String description = command.split("/")[0].split(" ", 2)[1].trim();
                    String by = command.split("/")[1].split(" ", 2)[1].trim();

                    System.out.println("Deadline task");
                    System.out.println(description);
                    System.out.println(by);

                    return new DeadlineTask(description,false, by);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new InvalidTaskException("OOPS!!! The description or by of a deadline cannot be empty.");
                } catch (InvalidDateException e) {
                    throw new InvalidTaskException("OOPS!!! Please enter a valid " +
                            "date and time in the format yyyy-mm-dd.");
                }
            }
            case EVENT: {
                try {
                    String[] commands = command.split("/");
                    String description = commands[0].split(" ", 2)[1].trim();
                    String date = commands[1].split(" ", 2)[1].split(" ")[0].trim();
                    String startTime = commands[1].split(" ", 2)[1].split(" ")[1].trim();
                    String endTime = commands[2].split(" ", 2)[1].trim();

                    System.out.println("Event task");
                    System.out.println(description);
                    System.out.println(date);
                    System.out.println(startTime);
                    System.out.println(endTime);

                    return new EventTask(description,false, date, startTime, endTime);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new InvalidTaskException("OOPS!!! The description, start time or " +
                            "end time of an event cannot be empty.");
                } catch (InvalidDateException e) {
                    throw new InvalidTaskException("OOPS!!! Please enter a valid " +
                            "date in the format yyyy-mm-dd.");
                }
            }
            default:
                throw new InvalidTaskException("OOPS!!! I'm sorry, but I don't what task is this. :-(");
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
