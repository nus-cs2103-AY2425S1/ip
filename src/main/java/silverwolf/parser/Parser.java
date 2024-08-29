package silverwolf.parser;

import java.time.LocalDateTime;

import silverwolf.command.AddCommand;
import silverwolf.command.Command;
import silverwolf.command.DeleteCommand;
import silverwolf.command.ExitCommand;
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
        String commandWord = parts[0];
        String arguments = parts.length > 1 ? parts[1] : "";

        switch (commandWord) {
        case "bye":
            return new ExitCommand(); // Exit the chat bot
        case "list":
            return new ListCommand(); // List all tasks
        case "delete":
            return new DeleteCommand(arguments); // Delete a task by its index
        case "mark":
            return new MarkCommand(arguments); // Mark a task as done
        case "unmark":
            return new UnmarkCommand(arguments); // Unmark a task as not done
        case "todo":
            return new AddCommand(new Todo(arguments)); // Add a todo task
        case "deadline":
            return new AddCommand(parseDeadLine(arguments)); // Add a Deadline task
        case "event":
            return new AddCommand(parseEvent(arguments)); // Add an Event task
        default:
            throw new SilverWolfException("Unknown command: " + commandWord); // Handle unrecognized commands
        }
    }


    /**
     * Parses the arguments for an event command and returns an Event object.
     *
     * @param arugments The string containing the description and date/time information of the event.
     * @return The Event object created from the parsed input.
     * @throws SilverWolfException If the input format is incorrect or essential details are missing.
     */
    private static Event parseEvent(String arugments) throws SilverWolfException {
        try {
            // Split the input into description and date/time components
            String[] parts = arugments.split(" /from ");
            String[] to = parts[1].split(" /to ");
            DateTimeParser parser = new DateTimeParser();
            // Parse the string into a LocalDateTime object
            LocalDateTime eventFrom = parser.parseDateTime(to[0]);
            LocalDateTime eventTo = parser.parseDateTime(to[1]);
            // Format the LocalDateTime object into a string
            String formattedFromDateTime = parser.formatDateTime(eventFrom);
            String formattedToDateTime = parser.formatDateTime(eventTo);
            return new Event(parts[0], formattedFromDateTime, formattedToDateTime);

        } catch (StringIndexOutOfBoundsException e) {
            throw new SilverWolfException("Hey! your event cannot be empty you know");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SilverWolfException("Wrong usage. Correct usage: event [task in String] "
                   + "/from [date/time] /to [date/time] e.g event project meeting /from Mon 2pm /to 4pm ");
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
        if (parts.length < 2) {
            throw new SilverWolfException("Wrong usage. Correct usage: deadline [task in String] /by "
                    + "[date/time] \" +\n"
                    + " \"e.g deadline submit report /by 11/10/2019 ");
        }
        try {
            String description = parts[0].trim();
            String by = parts[1].trim();
            DateTimeParser parser = new DateTimeParser();
            // Parse the string into a LocalDateTime object
            LocalDateTime deadlineDateTime = parser.parseDateTime(by);
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
