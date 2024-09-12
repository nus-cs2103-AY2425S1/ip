package revir.user;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

import revir.system.exceptions.IllegalCommandException;
import revir.system.exceptions.InvalidFormatException;
import revir.tasks.Deadline;
import revir.tasks.Event;
import revir.tasks.Todo;
import revir.user.command.Command;
import revir.user.command.Create;
import revir.user.command.Delete;
import revir.user.command.Find;
import revir.user.command.ListTasks;
import revir.user.command.Mark;
import revir.user.command.Nop;

/**
 * Parses the command string and returns the corresponding Command object.
 */
public class Parser {
    private enum Action {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND, INVALID
    }

    private static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final Map<String, Action> actionMap = new HashMap<>();
    static {
        actionMap.put("bye", Action.BYE);
        actionMap.put("list", Action.LIST);
        actionMap.put("mark", Action.MARK);
        actionMap.put("unmark", Action.UNMARK);
        actionMap.put("todo", Action.TODO);
        actionMap.put("deadline", Action.DEADLINE);
        actionMap.put("event", Action.EVENT);
        actionMap.put("delete", Action.DELETE);
        actionMap.put("find", Action.FIND);
    }

    /**
     * Parses the command string and returns the corresponding Command object.
     *
     * @param commandString The command string to parse.
     * @return The Command object corresponding to the command string.
     * @throws IllegalCommandException If the command is not recognized.
     * @throws InvalidFormatException If the command is recognized but the format is invalid.
     */
    public Command parse(String commandString) throws IllegalCommandException, InvalidFormatException {
        String[] split = commandString.trim().split(" ", 2);
        String commandWord = split[0].toLowerCase();
        String commandArgs = split.length > 1 ? split[1] : "";
        Action action = Parser.actionMap.getOrDefault(commandWord, Action.INVALID);
        // use switch case to return different methods based on command type
        switch (action) {
        case BYE:
            return new Nop(true);
        case LIST:
            return new ListTasks();
        case MARK:
            return parseMark(commandArgs);
        case UNMARK:
            return parseUnmark(commandArgs);
        case TODO:
            return parseTodo(commandArgs);
        case DEADLINE:
            return parseDeadline(commandArgs);
        case EVENT:
            return parseEvent(commandArgs);
        case DELETE:
            return parseDelete(commandArgs);
        case FIND:
            return parseFind(commandArgs);
        case INVALID:
            throw new IllegalCommandException(commandWord);
        default: 
            // should never reach here
            assert(false);
            return new Nop(false);
        }
    }

    Command parseMark(String args) {
        int taskIndex = Integer.parseInt(args);
        return new Mark(taskIndex, true);
    }

    Command parseUnmark(String args) {
        int taskIndex = Integer.parseInt(args);
        return new Mark(taskIndex, false);
    }

    Command parseFind(String args) throws InvalidFormatException {
        return new Find(args);
    }

    Command parseTodo(String args) throws InvalidFormatException {
        if (args.isEmpty()) {
            throw new InvalidFormatException(Todo.format());
        }
        return new Create(args);
    }

    Command parseDeadline(String args) throws InvalidFormatException {
        String[] taskInfo = args.split(" /by ");
        if (taskInfo.length != 2) {
            throw new InvalidFormatException(Deadline.format());
        }

        String taskDescription = taskInfo[0];
        String deadlineStr = taskInfo[1];
    
        try {
            LocalDateTime deadline = LocalDateTime.parse(deadlineStr, dateFormat);
            return new Create(taskDescription, deadline);
        } catch (DateTimeParseException e) {
            throw new InvalidFormatException(Deadline.format());
        }
    }

    Command parseEvent(String args) throws InvalidFormatException {
        String[] taskInfo = args.split(" /from ");
        if (taskInfo.length != 2) {
            throw new InvalidFormatException(Event.format());
        }

        String taskDescription = taskInfo[0];
        String startDateStr = taskInfo[1].split(" /to ")[0];
        String endDateStr = taskInfo[1].split(" /to ")[1];
        
        try {
            LocalDateTime startDate = LocalDateTime.parse(startDateStr, dateFormat);
            LocalDateTime endDate = LocalDateTime.parse(endDateStr, dateFormat);
            return new Create(taskDescription, startDate, endDate);
        } catch (DateTimeParseException e) {
            throw new InvalidFormatException(Event.format());
        }
    }

    Command parseDelete(String args) {
        int taskIndex = Integer.parseInt(args);
        return new Delete(taskIndex);
    }

}
