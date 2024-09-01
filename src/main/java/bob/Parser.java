package bob;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import bob.task.Task;
import bob.task.ToDo;
import bob.task.Deadline;
import bob.task.Event;

public class Parser {

    public static Bob.Command parseCommand(String userInput) throws BobException {
        if (userInput.isEmpty() || userInput.equals(" ")) {
            throw new BobException("You did not key in anything...");
        }
        String commandStr = userInput.split(" ", 2)[0].toUpperCase();
        try {
            return Bob.Command.valueOf(commandStr);
        } catch (IllegalArgumentException e) {
            return Bob.Command.UNKNOWN;
        }
    }
    public static Task parseTask(String line) throws BobException {
        String[] details = line.split(",");
        String type = details[0];
        boolean isDone = details[1].equals("1");
        String description = details[2];

        switch (type) {
        case "T":
            return new ToDo(description, isDone);
        case "D":
            String byStr = details[3];
            LocalDateTime by = parseDateTime(byStr);
            return new Deadline(description, isDone, by);
        case "E":
            String fromStr = details[3];
            String toStr = details[4];
            LocalDateTime from = parseDateTime(fromStr);
            LocalDateTime to = parseDateTime(toStr);
            return new Event(description, isDone, from, to);
        default:
            throw new BobException("Unknown task type: " + type);
        }
    }

    public static String getTaskDetails(String userInput) {
        String[] args = userInput.split(" ", 2);
        if (args.length < 2) {
            return "";
        }
        return args[1].trim();
    }

    public static LocalDateTime parseDateTime(String dateTimeStr) throws BobException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            return LocalDateTime.parse(dateTimeStr, formatter);
        } catch (DateTimeParseException e) {
            throw new BobException(
                    "Please provide the correct date and 24-hour time format: yyyy-mm-dd HHmm\n"
                    + "Eg. 2024-08-27 1530 for Aug 27 2024 03.30pm");
        }
    }

    public static String getDateTimeStr(LocalDateTime dateTimeStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return dateTimeStr.format(formatter);
    }
}
