package Naega.Parser;

import Naega.Command.*;
import Naega.NaegaException;
import Naega.Task.Deadline;
import Naega.Task.Event;
import Naega.Task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    /**
     * Parses the user input into a Command object.
     *
     * @param userInput the input command from the user
     * @return the Command object corresponding to the user input
     * @throws NaegaException if the command is unknown or improperly formatted
     */
    public static Command parse(String userInput) throws NaegaException {
        String[] words = userInput.split(" ", 2);
        String[] splitCommand = userInput.split(" ", 2);
        String commandWord = splitCommand[0];

        switch (commandWord) {
            case "todo":
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
                if (splitCommand.length < 2) {
                    throw new NaegaException("Please specify the task number to delete.");
                }
                int taskIndex = Integer.parseInt(splitCommand[1]);
                return new DeleteCommand(taskIndex);
            case "find":
                if (splitCommand.length < 2) {
                    throw new NaegaException("Please specify a keyword to search.");
                }
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
        String[] parts = args.split("/from", 2);
        if (parts.length < 2) {
            throw new NaegaException("The format for an event must include /from.");
        }

        String description = parts[0].trim();

        // Split to get 'from' and 'to' times
        String[] timeParts = parts[1].split("/to", 2);
        if (timeParts.length < 2) {
            throw new NaegaException("The format for an event must include /to.");
        }

        LocalDateTime from = LocalDateTime.parse(timeParts[0].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        LocalDateTime to = LocalDateTime.parse(timeParts[1].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));

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
        String[] parts = args.split("/by", 2);
        if (parts.length < 2) {
            throw new NaegaException("The format for a deadline must include /by.");
        }
        String description = parts[0].trim();
        LocalDateTime by = LocalDateTime.parse(parts[1].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        return new AddCommand(new Deadline(description, by));
    }
}