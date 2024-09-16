package com.appleaster.parser;

import com.appleaster.command.Command;
import com.appleaster.command.CommandType;
import com.appleaster.exception.AppleasterException;
import com.appleaster.task.Todo;
import com.appleaster.task.Deadline;
import com.appleaster.task.Event;
import com.appleaster.task.Todo;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parses user input into Command objects for the Appleaster application.
 */
public class Parser {
  private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ISO_LOCAL_DATE;

    /**
     * Parses a user input string into a Command object.
     *
     * @param input The user input string to parse.
     * @return A Command object representing the parsed input.
     * @throws AppleasterException If the input is invalid or cannot be parsed.
     */
    public static Command parseCommand(String input) throws AppleasterException {
        assert input != null : "Input string cannot be null";
        String[] parts = input.split("\\s+", 2);
        assert parts.length > 0 : "Input string cannot be empty";
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
      case "find":
        return parseFind(arguments);                
      case "bye":
        return new Command(CommandType.BYE);
      case "view":
        return parseViewSchedule(arguments);        
      default:
        throw new AppleasterException("I don't recognize that command. "
            + "Here are the commands I know: todo, deadline, event, list, "
            + "mark, unmark, delete, date, bye.");
    }
  }

    /**
     * Parses a mark or unmark command.
     *
     * @param commandWord The command word ("mark" or "unmark").
     * @param arguments The arguments following the command word.
     * @return A Command object representing a mark or unmark action.
     * @throws AppleasterException If the arguments are invalid.
     */
    private static Command parseMarkUnmark(String commandWord, String arguments) throws AppleasterException {
        assert commandWord != null && (commandWord.equals("mark") || commandWord.equals("unmark")) : "Invalid command word for mark/unmark";
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

    /**
     * Parses a find command.
     *
     * @param keyword The keyword to search for.
     * @return A Command object representing a find action.
     * @throws AppleasterException If the keyword is empty.
     */

    private static Command parseFind(String keyword) throws AppleasterException {
        if (keyword.trim().isEmpty()) {
          throw new AppleasterException("The search keyword cannot be empty.");
        }
        return new Command(CommandType.FIND, keyword.trim());
      }    

    /**
     * Parses a todo command.
     *
     * @param description The description of the todo task.
     * @return A Command object representing a todo task.
     * @throws AppleasterException If the todo description is empty.
     */
    private static Command parseTodo(String description) throws AppleasterException {
        assert description != null : "Todo description cannot be null";
        if (description.trim().isEmpty()) {
            throw new AppleasterException("The description of a todo cannot be empty. Please provide a description after 'todo'.");
        }
        return new Command(CommandType.TODO, new Todo(description));
    }

    /**
     * Parses a deadline command.
     *
     * @param arguments The arguments for the deadline command.
     * @return A Command object representing a deadline task.
     * @throws AppleasterException If the deadline format is invalid.
     */
  private static Command parseDeadline(String arguments) throws AppleasterException {
    assert arguments != null : "Deadline arguments cannot be null";
    String[] parts = arguments.split(" /by ");
    assert parts.length == 2 : "Deadline must have a description and a date";
    if (parts.length != 2) {
      throw new AppleasterException("Invalid deadline format. "
          + "Please use: deadline <description> /by yyyy-MM-dd HHmm");
    }
    if (parts[0].trim().isEmpty()) {
      throw new AppleasterException("The description of a deadline cannot be empty.");
    }
    if (parts[1].trim().isEmpty()) {
      throw new AppleasterException("The deadline date/time cannot be empty.");
    }
    return new Command(CommandType.DEADLINE, new Deadline(parts[0], parts[1]));
  }

    /**
     * Parses an event command.
     *
     * @param arguments The arguments for the event command.
     * @return A Command object representing an event task.
     * @throws AppleasterException If the event format is invalid.
     */
  private static Command parseEvent(String arguments) throws AppleasterException {
    assert arguments != null : "Event arguments cannot be null";
    String[] parts = arguments.split(" /from | /to ");
    assert parts.length == 3 : "Event must have a description, start time, and end time";
    if (parts.length != 3) {
      throw new AppleasterException("Invalid event format. "
          + "Please use: event <description> /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm");
    }
    if (parts[0].trim().isEmpty()) {
      throw new AppleasterException("The description of an event cannot be empty.");
    }
    if (parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
      throw new AppleasterException("The start and end times of an event cannot be empty.");
    }
    return new Command(CommandType.EVENT, new Event(parts[0], parts[1], parts[2]));
  }

    /**
     * Parses a delete command.
     *
     * @param arguments The arguments for the delete command.
     * @return A Command object representing a delete action.
     * @throws AppleasterException If the delete command format is invalid.
     */
  private static Command parseDelete(String arguments) throws AppleasterException {
    assert arguments != null : "Delete arguments cannot be null";
    if (arguments.isEmpty()) {
      throw new AppleasterException("Please provide a task number to delete. "
          + "For example: delete 1");
    }
    try {
      int index = Integer.parseInt(arguments) - 1;
      return new Command(CommandType.DELETE, index);
    } catch (NumberFormatException e) {
      throw new AppleasterException("The task number should be a valid integer. "
          + "You provided: " + arguments);
    }
  }

    /**
     * Parses a date command.
     *
     * @param arguments The arguments for the date command.
     * @return A Command object representing a date filter action.
     * @throws AppleasterException If the date format is invalid.
     */
  private static Command parseDate(String arguments) throws AppleasterException {
    assert arguments != null : "Date argument cannot be null";
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

  private static Command parseViewSchedule(String arguments) throws AppleasterException {
    if (arguments.trim().isEmpty()) {
        throw new AppleasterException("Please provide a date to view the schedule. For example: view 2024-09-16");
    }
    try {
        LocalDate date = LocalDate.parse(arguments.trim(), DATE_FORMAT);
        return new Command(CommandType.VIEW_SCHEDULE, date);
    } catch (DateTimeParseException e) {
        throw new AppleasterException("Invalid date format. Please use: yyyy-MM-dd");
    }
  }
}