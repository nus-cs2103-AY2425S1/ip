package Naega.Parser;

import Naega.Command.*;
import Naega.NaegaException;
import Naega.Task.Deadline;
import Naega.Task.Event;
import Naega.Task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Parses user input into executable commands.
 */
public class Parser {

    /**
     * Parses the user input into a Command object.
     *
     * @param userInput the input command from the user
     * @return the Command object corresponding to the user input
     * @throws NaegaException if the command is unknown or improperly formatted
     */
    public static Command parse(String userInput) throws NaegaException {
        assert userInput != null && !userInput.trim().isEmpty() : "User input must not be null or empty";
        String[] words = userInput.split(" ", 2);
        String[] splitCommand = userInput.split(" ", 2);
        String commandWord = splitCommand[0];

        switch (commandWord) {
            case "todo":
                assert words.length == 2 : "Todo command must contain a description";
                return new AddCommand(new Todo(words[1]));
            case "event":
                return parseEvent(words[1]);
            case "deadline":
                return parseDeadline(words[1]);
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(userInput);
            case "unmark":
                return new UnmarkCommand(userInput);
            case "delete":
                assert splitCommand.length == 2 : "Delete command must specify a task number";
                int taskIndex = Integer.parseInt(splitCommand[1]);
                return new DeleteCommand(taskIndex);
            case "find":
                assert splitCommand.length == 2 : "Find command must specify a keyword";
                return new FindCommand(splitCommand[1]);
            default:
                throw new NaegaException("I don't understand the command.");
        }
    }

    /**
     * Parses the arguments for an event command and creates an AddCommand for it.
     *
     * @param args the arguments for the event command
     * @return the AddCommand for the event
     * @throws NaegaException if the arguments are improperly formatted
     */
    private static Command parseEvent(String args) throws NaegaException {
        assert args != null && !args.trim().isEmpty() : "Event command must not be null or empty";
        String[] parts = args.split("/from", 2);
        assert parts.length == 2 : "The format for an event must include /from";

        String description = parts[0].trim();

        String[] timeParts = parts[1].split("/to", 2);
        assert timeParts.length == 2 : "The format for an event must include /to";

        LocalDateTime from = LocalDateTime.parse(timeParts[0].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        LocalDateTime to = LocalDateTime.parse(timeParts[1].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));

        assert from.isBefore(to) : "'From' date must be before 'to' date for event";
        return new AddCommand(new Event(description, from, to));
    }

    /**
     * Parses the arguments for a deadline command and creates an AddCommand for it.
     *
     * @param args the arguments for the deadline command
     * @return the AddCommand for the deadline
     * @throws NaegaException if the arguments are improperly formatted
     */
    private static Command parseDeadline(String args) throws NaegaException {
        assert args != null && !args.trim().isEmpty() : "Deadline command must not be null or empty";
        String[] parts = args.split("/by", 2);
        assert parts.length == 2 : "The format for a deadline must include /by";
        String description = parts[0].trim();
        LocalDateTime by = LocalDateTime.parse(parts[1].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        return new AddCommand(new Deadline(description, by));
    }
}