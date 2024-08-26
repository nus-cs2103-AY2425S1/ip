package luna;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import luna.command.*;
import luna.task.*;

public class Parser {
    enum CommandType {
        LIST,
        TODO,
        DEADLINE,
        EVENT,
        MARK,
        UNMARK,
        DELETE,
        BYE,
    }

    public static Command parse(String input) throws LunaException {

        if (input.isEmpty()) {
            throw new LunaException("""
                    Please enter one of the following commands:
                    "todo", "deadline", "event"
                    "mark", "unmark", "delete"
                    "list", "bye"
                    """);
        }

        String[] commands = input.split(" ", 2);
        String command = commands[0];
        CommandType commandType;

        try {
            commandType = CommandType.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new LunaException("""
                    Invalid Command!
                    Please enter one of the following commands:
                    "todo", "deadline", "event"
                    "mark", "unmark", "delete"
                    "list", "bye"
                    """);
        }

        switch (commandType) {
        case BYE:
            return new ExitCommand();

        case LIST:
            return new ListCommand();

        case MARK:
            if (commands.length == 1) {
                throw new LunaException("Indicate the task number to mark as done e.g. mark 2");
            }

            int taskToMark;

            try {
                taskToMark = Integer.parseInt(commands[1]) - 1;
            } catch (NumberFormatException e) {
                throw new LunaException("Invalid task reference. Use integers only.");
            }

            return new MarkCommand(taskToMark);

        case UNMARK:
            if (commands.length == 1) {
                throw new LunaException("Indicate the task number to unmark e.g. unmark 2");
            }

            int taskToUnmark;

            try {
                taskToUnmark = Integer.parseInt(commands[1]) - 1;
            } catch (NumberFormatException e) {
                throw new LunaException("Invalid task reference. Use integers only.");
            }

            return new UnmarkCommand(taskToUnmark);

        case DELETE:
            if (commands.length == 1) {
                throw new LunaException("Indicate the task number to delete e.g. delete 2");
            }

            int taskToDelete;

            try {
                taskToDelete = Integer.parseInt(commands[1]) - 1;
            } catch (NumberFormatException e) {
                throw new LunaException("Invalid task reference. Use integers only.");
            }

            return new DeleteCommand(taskToDelete);

        case TODO:
            if (commands.length == 1 || commands[1].trim().isEmpty()) {
                throw new LunaException("Enter description for todo e.g. todo [description]");
            }

            Todo todo = new Todo(commands[1]);
            return new TodoCommand(todo);

        case DEADLINE:
            if (commands.length == 1 || commands[1].trim().isEmpty() || commands[1].trim().indexOf("/") == 0) {
                throw new LunaException("Enter description for deadline e.g. " +
                        "deadline return book /by Sunday");
            }

            String[] deadline = commands[1].split(" /");

            if (deadline.length == 1) {
                throw new LunaException("Enter deadline for task e.g. deadline [task] /by [deadline]");
            }

            if (!deadline[1].contains("by ") || deadline[1].trim().length() <= 2) {
                throw new LunaException("Enter deadline for task " +
                        "e.g. deadline [task] /by [dd/MM/yyyy HH:mm]");
            }

            LocalDateTime deadlineDateTime;

            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                deadlineDateTime = LocalDateTime.parse(deadline[1].substring(3), formatter);

                if (deadlineDateTime.isBefore(LocalDateTime.now())) {
                    throw new LunaException("Invalid task: Deadline is before current time");
                }
            } catch (DateTimeParseException e) {
                throw new LunaException("Enter deadline using format: dd/MM/yyyy HH:mm. " +
                        "eg. 14/02/2024 14:30");
            }

            Deadline deadlineTask = new Deadline(deadline[0], deadlineDateTime);
            return new DeadlineCommand(deadlineTask);

        case EVENT:
            if (commands.length == 1 || commands[1].trim().isEmpty() || commands[1].trim().indexOf("/") == 0) {
                throw new LunaException("Enter description for event e.g. " +
                        "event project meeting /from Mon 2pm /to 4pm");
            }

            if (!commands[1].contains("/from ") || !commands[1].contains("/to ")) {
                throw new LunaException("Enter start and end time for event " +
                        "e.g. event [task] /from [startTime] /to [endTime]");
            }

            String[] event = commands[1].split(" /");

            if (!(event[1].contains("from ") && event[1].trim().length() > 5) ||
                    !(event[2].contains("to ") && event[2].trim().length() > 3)) {
                throw new LunaException("Enter start and end time for event using the format: " +
                        "event [task] /from [dd/MM/yyyy HH:mm] /to [dd/MM/yyyy HH:mm]");
            }

            LocalDateTime startTime;
            LocalDateTime endTime;

            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                startTime = LocalDateTime.parse(event[1].substring(5), formatter);
                endTime = LocalDateTime.parse(event[2].substring(3), formatter);

                if (startTime.isAfter(endTime)) {
                    throw new LunaException("Invalid Event: Start is after End");
                }

                if (startTime.isBefore(LocalDateTime.now())) {
                    throw new LunaException("Invalid Event: Start is before current time");
                }
            } catch (DateTimeParseException e) {
                throw new LunaException("Enter start and end time using format: dd/MM/yyyy HH:mm. " +
                        "eg. 14/02/2024 14:30");
            }

            Event eventTask = new Event(event[0], startTime, endTime);
            return new EventCommand(eventTask);

        default:
            throw new LunaException("""
                    Invalid Command!
                    Please enter one of the following commands:
                    "todo", "deadline", "event"
                    "mark", "unmark", "delete"
                    "list", "bye"
                    """);
        }
    }
}
