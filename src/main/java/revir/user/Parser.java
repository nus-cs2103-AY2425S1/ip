package revir.user;

import revir.system.Exceptions.IllegalCommandException;
import revir.system.Exceptions.InvalidFormatException;
import revir.tasks.*;
import revir.user.command.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    // use java enum to define command types
    enum Action {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND, INVALID
    }

    static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    // create and statically define hashmap to map string to command type
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

    public Command parse(String commandString) throws IllegalCommandException, InvalidFormatException {
        String split[] = commandString.split(" ", 2);
        String commandWord = split[0].toLowerCase();
        String args = split.length > 1 ? split[1] : "";
        Action action = Parser.actionMap.getOrDefault(commandWord, Action.INVALID);
        // use switch case to return different methods based on command type
        switch (action) {
            case BYE:
                return new Nop(true);
            case LIST:
                return new ListTasks();
            case MARK:
                return parseMark(args);
            case UNMARK:
                return parseUnmark(args);
            case TODO:
                return parseTodo(args);
            case DEADLINE:
                return parseDeadline(args);
            case EVENT:
                return parseEvent(args);
            case DELETE:
                return parseDelete(args);
            case FIND:
                return parseFind(args);
            case INVALID:
                throw new IllegalCommandException(commandWord);
            default:
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
            throw new InvalidFormatException(Todo.format);
        }
        return new Create(args);
    }

    Command parseDeadline(String args) throws InvalidFormatException {
        String[] taskInfo = args.split(" /by ");
        if (taskInfo.length == 2) {
            String taskDescription = taskInfo[0];
            String deadlineStr = taskInfo[1];
            try {
                LocalDateTime deadline = LocalDateTime.parse(deadlineStr, dateFormat);
                return new Create(taskDescription, deadline);
            } catch (DateTimeParseException e) {
                throw new InvalidFormatException(Deadline.format);
            }
        } else {
            throw new InvalidFormatException(Deadline.format);
        }
    }

    Command parseEvent(String args) throws InvalidFormatException {
        String[] taskInfo = args.split(" /from ");
        if (taskInfo.length == 2) {
            String taskDescription = taskInfo[0];
            String startDateStr = taskInfo[1].split(" /to ")[0];
            String endDateStr = taskInfo[1].split(" /to ")[1];
            try {
                LocalDateTime startDate = LocalDateTime.parse(startDateStr, dateFormat);
                LocalDateTime endDate = LocalDateTime.parse(endDateStr, dateFormat);
                return new Create(taskDescription, startDate, endDate);
            } catch (DateTimeParseException e) {
                throw new InvalidFormatException(Event.format);
            }
        } else {
            throw new InvalidFormatException(Event.format);
        }
    }

    Command parseDelete(String args) {
        int taskIndex = Integer.parseInt(args);
        return new Delete(taskIndex);
    }

}
