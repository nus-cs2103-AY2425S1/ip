import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public static String DATE_OUTPUT_PATTERN = "MMM d yyy";
    public static String DATE_INPUT_PATTERN = "dd/M/yyy";

    public static Task parseStringToTask(String string) {
        String regex = "\\d+\\. \\[(?<taskType>[A-Z])\\]\\[(?<status>[ X])\\] (?<name>[^\\(]+)(?:\\(by: (?<by>[^\\)]+)\\))?(?:\\(from: (?<from>[^\\)]+) to: (?<to>[^\\)]+)\\))?";

        Pattern r = Pattern.compile(regex);
        Matcher m = r.matcher(string);

        if (m.find()) {
            String taskType = m.group("taskType");      // Task type (e.g., "T")
            boolean isDone = m.group("status").trim().equals("X"); // Task completion status (e.g., "X" or " ")
            String taskName = m.group("name").trim(); // Task name
            String fromTime = m.group("from");      // From timeframe (if present)
            String toTime = m.group("to");        // To timeframe (if present)
            String byTime = m.group("by");        // By timeframe (if present)

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_OUTPUT_PATTERN);

            switch (taskType) {
                case "T":
                    Todo newTodo = new Todo(taskName);
                    if (isDone) {
                        newTodo.markAsDone();
                    }
                    return newTodo;
                case "D":
                    LocalDate formattedTime = LocalDate.parse(byTime, formatter);

                    Deadline newDeadline = new Deadline(taskName, formattedTime);
                    if (isDone) {
                        newDeadline.markAsDone();
                    }
                    return newDeadline;
                case "E":
                    LocalDate formattedStartTime = LocalDate.parse(fromTime, formatter);
                    LocalDate formattedEndTime = LocalDate.parse(toTime, formatter);

                    Event newEvent = new Event(taskName, formattedStartTime, formattedEndTime);
                    if (isDone) {
                        newEvent.markAsDone();
                    }
                    return newEvent;
            }

        }

        return null;
    }

    public static LocalDate parseStringToDate(String stringDate) throws SentinelException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
            return LocalDate.parse(stringDate.trim(), formatter);
        } catch (DateTimeParseException e) {
            throw new SentinelException("Invalid date time format, I can only read formats in dd/M/yyyy pattern!");
        }
    }
}
