package com.appleaster.parser;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.appleaster.command.Command;
import com.appleaster.command.CommandType;
import com.appleaster.exception.AppleasterException;
import com.appleaster.task.Deadline;
import com.appleaster.task.Event;
import com.appleaster.task.Todo;

public class Parser {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ISO_LOCAL_DATE;

    public static Command parseCommand(String input) throws AppleasterException {
        String[] parts = input.split("\\s+", 2);
        String commandWord = parts[0].toLowerCase();
        String arguments = parts.length > 1 ? parts[1] : "";

        switch (commandWord) {
            case "list":
                return new Command(CommandType.LIST);
            case "mark":
            case "unmark":
                return parseMarkUnmark(commandWord, arguments);
            case "todo":
                return parseTodo(arguments);
            case "deadline":
                return parseDeadline(arguments);
            case "event":
                return parseEvent(arguments);
            case "delete":
                return parseDelete(arguments);
            case "date":
                return parseDate(arguments);
            case "bye":
                return new Command(CommandType.BYE);
            default:
                throw new AppleasterException("I don't recognize that command. Here are the commands I know: todo, deadline, event, list, mark, unmark, delete, date, bye.");
        }
    }

    private static Command parseMarkUnmark(String commandWord, String arguments) throws AppleasterException {
        if (arguments.isEmpty()) {
            throw new AppleasterException("Please provide a task number to " + commandWord + ". For example: " + commandWord + " 1");
        }
        try {
            int index = Integer.parseInt(arguments) - 1;
            return new Command(commandWord.equals("mark") ? CommandType.MARK : CommandType.UNMARK, index);
        } catch (NumberFormatException e) {
            throw new AppleasterException("The task number should be a valid integer. You provided: " + arguments);
        }
    }

    private static Command parseTodo(String description) throws AppleasterException {
        if (description.trim().isEmpty()) {
            throw new AppleasterException("The description of a todo cannot be empty. Please provide a description after 'todo'.");
        }
        return new Command(CommandType.TODO, new Todo(description));
    }

    private static Command parseDeadline(String arguments) throws AppleasterException {
        String[] parts = arguments.split(" /by ");
        if (parts.length != 2) {
            throw new AppleasterException("Invalid deadline format. Please use: deadline <description> /by yyyy-MM-dd HHmm");
        }
        if (parts[0].trim().isEmpty()) {
            throw new AppleasterException("The description of a deadline cannot be empty.");
        }
        if (parts[1].trim().isEmpty()) {
            throw new AppleasterException("The deadline date/time cannot be empty.");
        }
        return new Command(CommandType.DEADLINE, new Deadline(parts[0], parts[1]));
    }

    private static Command parseEvent(String arguments) throws AppleasterException {
        String[] parts = arguments.split(" /from | /to ");
        if (parts.length != 3) {
            throw new AppleasterException("Invalid event format. Please use: event <description> /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm");
        }
        if (parts[0].trim().isEmpty()) {
            throw new AppleasterException("The description of an event cannot be empty.");
        }
        if (parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
            throw new AppleasterException("The start and end times of an event cannot be empty.");
        }
        return new Command(CommandType.EVENT, new Event(parts[0], parts[1], parts[2]));
    }

    private static Command parseDelete(String arguments) throws AppleasterException {
        if (arguments.isEmpty()) {
            throw new AppleasterException("Please provide a task number to delete. For example: delete 1");
        }
        try {
            int index = Integer.parseInt(arguments) - 1;
            return new Command(CommandType.DELETE, index);
        } catch (NumberFormatException e) {
            throw new AppleasterException("The task number should be a valid integer. You provided: " + arguments);
        }
    }

    private static Command parseDate(String arguments) throws AppleasterException {
        if (arguments.isEmpty()) {
            throw new AppleasterException("Please provide a date in the format: date yyyy-MM-dd");
        }
        try {
            LocalDate date = LocalDate.parse(arguments, DATE_FORMAT);
            return new Command(CommandType.DATE, date);
        } catch (DateTimeParseException e) {
            throw new AppleasterException("Invalid date format. Please use: yyyy-MM-dd");
        }
    }
}