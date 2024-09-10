package luna;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import luna.command.Command;
import luna.command.DeadlineCommand;
import luna.command.DeleteCommand;
import luna.command.EventCommand;
import luna.command.ExitCommand;
import luna.command.FindCommand;
import luna.command.ListCommand;
import luna.command.MarkCommand;
import luna.command.TodoCommand;
import luna.command.UndoCommand;
import luna.command.UnmarkCommand;
import luna.task.Deadline;
import luna.task.Event;
import luna.task.Todo;

/**
 * Parser for inputs given by the user through the chatbot.
 */
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
        FIND,
        UNDO,
    }

    /**
     * Returns a Command to be executed.
     *
     * @param input command from user.
     * @return Command to be executed.
     * @throws LunaException If input has invalid format.
     */
    public static Command parse(String input, Command previousCommand) throws LunaException {

        if (input.isEmpty()) {
            throw new LunaException("""
                    Please enter one of the following commands:
                    "todo", "deadline", "event" - add task of specified type to list
                    "mark" - mark a task as completed
                    "unmark" - unmark a task as not completed
                    "delete" - remove task from current tasks
                    "list" - show all tasks
                    "find" - search for task given description
                    "undo" - undo most recent command
                    "bye" - close chatbot
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
                    "todo", "deadline", "event" - add task of specified type to list
                    "mark" - mark a task as completed
                    "unmark" - unmark a task as not completed
                    "delete" - remove task from current tasks
                    "list" - show all tasks
                    "find" - search for task given description
                    "undo" - undo most recent command
                    "bye" - close chatbot
                    """);
        }

        switch (commandType) {
        case BYE:
            return new ExitCommand();

        case LIST:
            return new ListCommand(previousCommand);

        case UNDO:
            return new UndoCommand();

        case MARK:
            if (commands.length == 1) {
                throw new LunaException("Indicate the task number to mark as done\n"
                        + "e.g. mark 2");
            }

            int taskToMark;

            try {
                taskToMark = Integer.parseInt(commands[1].trim()) - 1;
            } catch (NumberFormatException e) {
                throw new LunaException("Invalid task reference. Use integers only.");
            }

            return new MarkCommand(taskToMark, previousCommand);

        case UNMARK:
            if (commands.length == 1) {
                throw new LunaException("Indicate the task number to unmark\n"
                        + "e.g. unmark 2");
            }

            int taskToUnmark;

            try {
                taskToUnmark = Integer.parseInt(commands[1].trim()) - 1;
            } catch (NumberFormatException e) {
                throw new LunaException("Invalid task reference. Use integers only.");
            }

            return new UnmarkCommand(taskToUnmark, previousCommand);

        case DELETE:
            if (commands.length == 1) {
                throw new LunaException("Indicate the task number to delete\n"
                        + "e.g. delete 2");
            }

            int taskToDelete;

            try {
                taskToDelete = Integer.parseInt(commands[1].trim()) - 1;
            } catch (NumberFormatException e) {
                throw new LunaException("Invalid task reference. Use integers only.");
            }

            return new DeleteCommand(taskToDelete, previousCommand);

        case FIND:
            if (commands.length == 1 || commands[1].trim().isEmpty()) {
                throw new LunaException("Enter task description to search\n"
                        + "e.g. find book");
            }
            return new FindCommand(commands[1].trim(), previousCommand);

        case TODO:
            if (commands.length == 1 || commands[1].trim().isEmpty()) {
                throw new LunaException("Enter description for todo\n"
                        + "e.g. todo [description]");
            }

            Todo todo = new Todo(commands[1].trim());
            return new TodoCommand(todo, previousCommand);

        case DEADLINE:
            if (commands.length == 1 || commands[1].trim().isEmpty()
                    || commands[1].trim().indexOf("/") == 0) {
                throw new LunaException("Enter description for deadline\n"
                        + "e.g. deadline return book /by 12/12/2024 12:00");
            }

            String[] deadline = commands[1].split(" /");

            if (deadline.length == 1) {
                throw new LunaException("Enter deadline for task\n"
                        + "e.g. deadline [task] /by [deadline]");
            }

            if (!deadline[1].contains("by ") || deadline[1].trim().length() <= 2) {
                throw new LunaException("Enter deadline for task\n"
                        + "e.g. deadline [task] /by [dd/MM/yyyy HH:mm]");
            }

            LocalDateTime deadlineDateTime;

            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                deadlineDateTime = LocalDateTime.parse(deadline[1].substring(3), formatter);

                if (deadlineDateTime.isBefore(LocalDateTime.now())) {
                    throw new LunaException("Invalid task: Deadline is before current time");
                }
            } catch (DateTimeParseException e) {
                throw new LunaException("Enter deadline using format: [dd/MM/yyyy HH:mm]\n"
                        + "e.g. 14/02/2024 14:30");
            }

            Deadline deadlineTask = new Deadline(deadline[0].trim(), deadlineDateTime);
            return new DeadlineCommand(deadlineTask, previousCommand);

        case EVENT:
            if (commands.length == 1 || commands[1].trim().isEmpty() || commands[1].trim().indexOf("/") == 0) {
                throw new LunaException("Enter description for event\n"
                        + "e.g. event project meeting /from 12/12/2024 12:00 /to 12/12/2024 16:00");
            }

            if (!commands[1].contains("/from ") || !commands[1].contains("/to ")) {
                throw new LunaException("Enter start and end time for event\n"
                        + "e.g. event [task] /from [startTime] /to [endTime]");
            }

            String[] event = commands[1].split(" /");

            if (!(event[1].contains("from ") && event[1].trim().length() > 5)
                    || !(event[2].contains("to ") && event[2].trim().length() > 3)) {
                throw new LunaException("Enter start and end time for event using the format:\n"
                        + "event [task] /from [dd/MM/yyyy HH:mm] /to [dd/MM/yyyy HH:mm]");
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
                throw new LunaException("Enter start and end time using format: [dd/MM/yyyy HH:mm]\n"
                        + "eg. 14/02/2024 14:30");
            }

            Event eventTask = new Event(event[0].trim(), startTime, endTime);
            return new EventCommand(eventTask, previousCommand);

        default:
            throw new LunaException("""
                    Please enter one of the following commands:
                    "todo", "deadline", "event" - add task of specified type to list
                    "mark" - mark a task as completed
                    "unmark" - unmark a task as not completed
                    "delete" - remove task from current tasks
                    "list" - show all tasks
                    "find" - search for task given description
                    "undo" - undo most recent command
                    "bye" - close chatbot
                    """);
        }
    }
}
