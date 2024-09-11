package dudu.utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import dudu.command.Command;
import dudu.command.CommandBye;
import dudu.command.CommandDeadline;
import dudu.command.CommandDelete;
import dudu.command.CommandEvent;
import dudu.command.CommandFind;
import dudu.command.CommandHelp;
import dudu.command.CommandList;
import dudu.command.CommandMark;
import dudu.command.CommandTodo;
import dudu.command.CommandUnmark;
import dudu.exception.InvalidFormatException;
import dudu.exception.MissingDateTimeException;
import dudu.exception.MissingDescriptionException;
import dudu.task.Deadline;
import dudu.task.Event;
import dudu.task.ToDo;

/**
 * Represents the class that parses user commands
 */
public class Parser {
    enum CommandType {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, FIND, HELP
    }

    /**
     * Parses a user command and returns the corresponding Command instance
     *
     * @param command User input
     * @return Respective Command instance
     * @throws MissingDescriptionException
     * @throws InvalidFormatException
     * @throws DateTimeParseException
     * @throws MissingDateTimeException
     */
    public static Command parse(String command) throws MissingDescriptionException,
            InvalidFormatException, DateTimeParseException, MissingDateTimeException {
        CommandType commandType = getCommandTypeFromInput(command);
        switch (commandType) {
        case BYE:
            return new CommandBye();
        case LIST:
            return new CommandList();
        case HELP:
            return new CommandHelp();
        case TODO:
            System.out.println("Something");
            return new CommandTodo(createTodo(command));
        case DEADLINE:
            return new CommandDeadline(createDeadline(command));
        case EVENT:
            return new CommandEvent(createEvent(command));
        case MARK:
            return new CommandMark(getIndex(command));
        case UNMARK:
            return new CommandUnmark(getIndex(command));
        case DELETE:
            return new CommandDelete(getIndex(command));
        case FIND:
            return new CommandFind(getContent(command));
        default:
            return new CommandHelp();
        }
    }

    /**
     * Get command type from user input
     *
     * @param command User input
     * @return Command type
     */
    public static CommandType getCommandTypeFromInput(String command) {
        String[] splitCommand = command.split(" ", 2);
        try {
            return CommandType.valueOf(splitCommand[0].trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            return CommandType.HELP;
        }
    }

    /**
     * Returns content after commandType
     *
     * @param command User input
     * @return Content after commandType
     * @throws MissingDescriptionException If content is empty
     */
    public static String getContent(String command) throws MissingDescriptionException {
        String[] splitCommand = command.split(" ", 2);
        if (splitCommand.length <= 1) {
            throw new MissingDescriptionException("Please include a description");
        }
        String content = splitCommand[1].trim();
        if (content.isEmpty()) {
            throw new MissingDescriptionException("Please include a description");
        }
        return content;
    }

    /**
     * Create a To-do task
     *
     * @param command User input without command at the front
     * @return Created to-do task
     * @throws MissingDescriptionException
     */
    public static ToDo createTodo(String command) throws MissingDescriptionException {
        return new ToDo(getContent(command));
    }

    /**
     * Create a Deadline task
     *
     * @param command User input without command at the front
     * @return Created deadline event
     * @throws MissingDescriptionException
     * @throws InvalidFormatException
     * @throws MissingDateTimeException
     */
    public static Deadline createDeadline(String command) throws MissingDescriptionException,
            InvalidFormatException, MissingDateTimeException {
        String content = getContent(command);
        if (!content.matches(".*/by.*")) {
            throw new InvalidFormatException("Please use deadline [description] /by [time]");
        }
        String[] details = content.split("/by", 2);
        if (details[0].trim().isEmpty()) {
            throw new MissingDescriptionException("Missing description after deadline");
        }
        if (details.length == 1 || details[1].trim().isEmpty()) {
            throw new MissingDateTimeException("Missing by time");
        }
        LocalDate by = LocalDate.parse(details[1].trim());
        return new Deadline(details[0].trim(), by);
    }

    /**
     * Create an event task
     *
     * @param command User input without command at the front
     * @return Created event task
     * @throws MissingDescriptionException
     * @throws InvalidFormatException
     * @throws MissingDateTimeException
     */
    public static Event createEvent(String command) throws MissingDescriptionException, InvalidFormatException, MissingDateTimeException {
        String content = getContent(command);
        if (!content.matches(".*/from.*/to.*")) {
            throw new InvalidFormatException("Please use event [description] /from [time] /to [time]");
        }
        String[] details = content.split("/from", 2);
        if (details[0].trim().isEmpty()) {
            throw new MissingDescriptionException("Missing description after event");
        }
        String description = details[0].trim();
        if (details.length == 1 || details[1].trim().isEmpty()) {
            throw new MissingDateTimeException("Missing from/to time");
        }
        String[] date = details[1].split("/to", 2);
        if (date.length <= 1 || date[0].trim().isEmpty() || date[1].trim().isEmpty()) {
            throw new MissingDateTimeException("Missing from/to time");
        }
        LocalDate from = LocalDate.parse(date[0].trim());
        LocalDate to = LocalDate.parse(date[1].trim());
        return new Event(description, from, to);
    }

    public static int getIndex(String command) throws MissingDescriptionException, IllegalArgumentException {
        String invalidNumberMessage = "Please input a valid number";
        String content = getContent(command);
        int index;
        try {
            index = Integer.parseInt(content) - 1;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(invalidNumberMessage);
        }
        if (index < 0) {
            throw new IllegalArgumentException(invalidNumberMessage);
        }
        return index;
    }
}
