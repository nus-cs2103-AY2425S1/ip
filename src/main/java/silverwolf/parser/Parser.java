package silverwolf.parser;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import silverwolf.command.AddCommand;
import silverwolf.command.Command;
import silverwolf.command.DeleteCommand;
import silverwolf.command.ExitCommand;
import silverwolf.command.FindCommand;
import silverwolf.command.ListCommand;
import silverwolf.command.MarkCommand;
import silverwolf.command.UnmarkCommand;
import silverwolf.exception.SilverWolfException;
import silverwolf.task.Deadline;
import silverwolf.task.Event;
import silverwolf.task.Todo;

/**
 * The Parser class is responsible for interpreting user input commands and
 * creating the corresponding Command objects for execution by the chatbot.
 */
public class Parser {

    /**
     * Parses the full command input by the user and returns the appropriate Command object.
     *
     * @param fullCommand The full input command string entered by the user.
     * @return The Command object that corresponds to the user's input.
     * @throws SilverWolfException If the command is not recognized or there is an error in the command format.
     */
    public static Command parse(String fullCommand) throws SilverWolfException {
        String[] parts = fullCommand.split(" ", 2);
        assert parts.length > 0 : "Command cannot be empty";
        String commandWord = parts[0];
        assert commandWord != null && !commandWord.isEmpty() : "Command word cannot be null or empty";
        String arguments = parts.length > 1 ? parts[1] : "";

        // Define friendly aliases for commands
        Map<String, String> aliasMap = new HashMap<>();
        aliasMap.put("t", "todo");
        aliasMap.put("d", "deadline");
        aliasMap.put("e", "event");
        aliasMap.put("x", "mark");
        aliasMap.put("u", "unmark");
        aliasMap.put("l", "list");
        aliasMap.put("b", "bye");
        aliasMap.put("f", "find");

        // Use aliases if present
        commandWord = aliasMap.getOrDefault(commandWord, commandWord);

        // Validate the command word
        if (commandWord.isEmpty()) {
            throw new SilverWolfException("Command word cannot be null or empty.");
        }

        switch (commandWord) {
        case "bye":
            return new ExitCommand(); // Exit the chat bot
        case "find":
            return new FindCommand(arguments); // Search for list
        case "list":
            return new ListCommand(); // List all tasks
        case "delete":
            return new DeleteCommand(arguments); // Delete a task by its index
        case "mark":
            return new MarkCommand(arguments); // Mark a task as done
        case "unmark":
            return new UnmarkCommand(arguments); // Unmark a task as not done
        case "todo":
            return new AddCommand(parseTodo(arguments)); // Add a todo task
        case "deadline":
            return new AddCommand(parseDeadLine(arguments)); // Add a Deadline task
        case "event":
            return new AddCommand(parseEvent(arguments)); // Add an Event task
        default:
            throw new SilverWolfException("Unknown command: " + commandWord); // Handle unrecognized commands
        }
    }

    /**
     * Parses the arguments for a todo command and returns a Todo object.
     *
     * @param arguments The string containing the description of the todo task.
     * @return The Todo object created from the parsed input.
     * @throws SilverWolfException If the input is empty or only contains whitespace.
     */
    private static Todo parseTodo(String arguments) throws SilverWolfException {
        if (arguments == null || arguments.trim().isEmpty()) {
            throw new SilverWolfException("The description of a todo cannot be empty. Format: todo <description>");
        }
        String description = arguments.trim();
        assert !description.isEmpty() : "Todo description cannot be empty after trimming";
        return new Todo(description);
    }
    /**
     * Parses the arguments for an event command and returns an Event object.
     *
     * @param arguments The string containing the description and date/time information of the event.
     * @return The Event object created from the parsed input.
     * @throws SilverWolfException If the input format is incorrect or essential details are missing.
     */
    private static Event parseEvent(String arguments) throws SilverWolfException {
        try {
            // Split the input into description and date/time components
            String[] parts = arguments.split(" /from ");
            if (parts.length < 2) {
                throw new SilverWolfException("Format: event <description> /from dd/mm/yyyy hhmm /to dd/mm/yyyy hhmm");
            }
            String[] to = parts[1].split(" /to ");
            if (to.length < 2) {
                throw new SilverWolfException("Format: event <description> /from dd/mm/yyyy hhmm /to dd/mm/yyyy hhmm");
            }
            DateTimeParser parser = new DateTimeParser();
            // Parse the string into a LocalDateTime object
            LocalDateTime eventFrom = parser.parseDateTime(to[0]);
            LocalDateTime eventTo = parser.parseDateTime(to[1]);
            assert eventFrom != null : "Event 'from' date/time cannot be null";
            assert eventTo != null : "Event 'to' date/time cannot be null";
            // Format the LocalDateTime object into a string
            String formattedFromDateTime = parser.formatDateTime(eventFrom);
            String formattedToDateTime = parser.formatDateTime(eventTo);
            return new Event(parts[0], formattedFromDateTime, formattedToDateTime);

        } catch (StringIndexOutOfBoundsException e) {
            throw new SilverWolfException("Hey! your event cannot be empty you know");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SilverWolfException("Wrong usage. Correct usage: event [task in String] "
                   + "/from [date/time] /to [date/time] e.g event project meeting /from 29/08/2024 1800 "
                   + "/to 31/08/2024 1940 ");
        }

    }

    /**
     * Parses the arguments for a deadline command and returns a Deadline object.
     *
     * @param arguments The string containing the description and due date/time information of the deadline.
     * @return The Deadline object created from the parsed input.
     * @throws SilverWolfException If the input format is incorrect or essential details are missing.
     */
    private static Deadline parseDeadLine(String arguments) throws SilverWolfException {
        String[] parts = arguments.split(" /by ");
        assert parts.length == 2 : "Deadline command must contain a description and a date/time";
        if (parts.length < 2) {
            throw new SilverWolfException("Wrong usage. Correct usage: deadline <description> /by dd/mm/yyyy hhmm"
                    + "[date/time] \" +\n"
                    + " \"e.g deadline submit report /by 11/10/2019 1810");
        }
        try {
            String description = parts[0].trim();
            if (description.isEmpty()) {
                throw new SilverWolfException("Deadline description cannot be empty.");
            }
            String by = parts[1].trim();
            if (by.isEmpty()) {
                throw new SilverWolfException("Deadline date/time cannot be empty.");
            }
            DateTimeParser parser = new DateTimeParser();
            // Parse the string into a LocalDateTime object
            LocalDateTime deadlineDateTime = parser.parseDateTime(by);
            assert deadlineDateTime != null : "Deadline date/time cannot be null";
            // Format the LocalDateTime object into a string
            String formattedDateTime = parser.formatDateTime(deadlineDateTime);
            return new Deadline(description, formattedDateTime);
        } catch (StringIndexOutOfBoundsException e) {
            throw new SilverWolfException("Hey! your deadline cannot be empty you know");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SilverWolfException("Wrong usage. Correct usage: deadline [task in String] /by [date/time] "
                   + "e.g deadline submit report /by 11/10/2019 ");
        }
    }
}
