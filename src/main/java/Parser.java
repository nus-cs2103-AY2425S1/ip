import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    /**
     * The method removes excess white space in all the Strings within the commandArray.
     *
     * @param commandArray Array containing the different Strings of commands.
     */
    public static void trimSplitCommands(String[] commandArray) {
        for (int i = 0; i < commandArray.length; i++) {
            commandArray[i] = commandArray[i].trim();
        }
    }

    /**
     * The method creates a LocalDateTime with the given dateString and timeString.
     *
     * @param dateString String representation of date.
     * @param timeString Time representation of date.
     * @return LocalDateTime of the dateString and timeString.
     * @throws DateTimeParseException when dateString and timeString are not in the correct format.
     */
    public static LocalDateTime createDateTime(String dateString, String timeString) throws DateTimeParseException {
        LocalDate date = LocalDate.parse(dateString, DATE_FORMATTER);
        LocalTime time = LocalTime.parse(timeString, TIME_FORMATTER);
        return LocalDateTime.of(date, time);
    }

    public String parseCommand(String line) {
        String command;
        if (line.isBlank()) {
            command = "";
        } else if (line.equals("list")) {
            command = "list";
        } else if (line.startsWith("mark")) {
            command = "mark";
        } else if (line.startsWith("unmark")) {
            command = "unmark";
        } else if (line.startsWith("delete")) {
            command = "delete";
        } else if (line.startsWith("todo")) {
            command = "todo";
        } else if (line.startsWith("deadline")) {
            command = "deadline";
        } else if (line.startsWith("event")) {
            command = "event";
        } else if (line.equals("bye")) {
            command = "bye";
        } else {
            command = line;
        }
        return command;
    }

    /**
     * The method parses for indexes in lines with instructions:
     * mark, unmark and delete.
     *
     * @param line
     * @return
     */
    public int parseIndex(String line) {
        return Integer.parseInt(line.split(" ")[1]) - 1;
    }

    public Object[] parseToDoTask(String task) {
        return new Object[] {task};
    }

    public Object[] parseDeadlineTask(String task) throws InvalidDeadlineContentException {
        String[] taskArray = task.split("/", 2);
        trimSplitCommands(taskArray);
        if (taskArray.length != 2) {
            throw new InvalidDeadlineContentException();
        }

        String taskDescription = taskArray[0];
        String taskDate = taskArray[1];

        String[] taskDateArray = taskArray[1].split(" ", 2);
        if (taskDateArray.length != 2) {
            throw new InvalidDeadlineContentException();
        }

        String date = taskDateArray[0];
        String time = taskDateArray[1];
        LocalDateTime dateAndTime = createDateTime(date, time);

        return new Object[] {taskDescription, dateAndTime};
    }

    public Object[] parseEventTask(String task) throws InvalidEventContentException {
        String[] taskArray = task.split("/", 3);
        trimSplitCommands(taskArray);
        if (taskArray.length != 3) {
            throw new InvalidEventContentException();
        }

        String taskDescription = taskArray[0];
        String taskStart = taskArray[1];
        String taskEnd = taskArray[2];

        String[] taskStartArray = taskStart.split(" ", 2);
        String[] taskEndArray = taskEnd.split(" ", 2);

        if (taskStartArray.length != 2 || taskEndArray.length != 2) {
            throw new InvalidEventContentException();
        }

        String startDate = taskStartArray[0];
        String startTime = taskStartArray[1];
        LocalDateTime startDateAndTime = createDateTime(startDate, startTime);

        String endDate = taskEndArray[0];
        String endTime = taskEndArray[1];
        LocalDateTime endDateAndTime = createDateTime(endDate, endTime);

        return new Object[] {taskDescription, startDateAndTime, endDateAndTime};
    }
}
