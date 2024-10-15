package niko.main;

import niko.command.AddCommand;
import niko.command.Command;
import niko.command.DeleteCommand;
import niko.command.ExitCommand;
import niko.command.FindCommand;
import niko.command.ListCommand;
import niko.command.MarkCommand;
import niko.command.SearchCommand;
import niko.command.UnmarkCommand;
import niko.common.NikoException;
import niko.task.Deadline;
import niko.task.Event;
import niko.task.Todo;

/**
 * The Parser class is responsible for parsing user input and creating corresponding Command objects.
 */
public class Parser {

    /**
     * Parses the full command input by the user and returns the appropriate Command object.
     *
     * @param fullCommand The full user input.
     * @return The corresponding Command object.
     * @throws NikoException If the command is invalid or improperly formatted.
     */
    public static Command parse(String fullCommand) throws NikoException {
        String[] words = fullCommand.trim().split(" ", 2);
        if (words.length == 0 || words[0].isEmpty()) {
            throw new NikoException("Please enter a valid command.");
        }
        String commandWord = words[0];
        return switch (commandWord) {
            case "todo" -> parseTodo(words);
            case "deadline" -> parseDeadline(words);
            case "event" -> parseEvent(words);
            case "list" -> new ListCommand();
            case "bye" -> new ExitCommand();
            case "delete" -> parseDelete(words);
            case "mark" -> parseMark(words);
            case "unmark" -> parseUnmark(words);
            case "search" -> parseSearch(words);
            case "find" -> parseFind(words);
            default -> throw new NikoException("I'm sorry, but I don't know what that means :-(");
        };
    }

    /**
     * Parses the 'todo' command and returns an AddCommand with a Todo task.
     *
     * @param words The command words array.
     * @return An AddCommand with a Todo task.
     * @throws NikoException If the todo description is empty.
     */
    private static Command parseTodo(String[] words) throws NikoException {
        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new NikoException("The description of a todo cannot be empty.");
        }
        return new AddCommand(new Todo(words[1].trim()));
    }

    /**
     * Parses the 'deadline' command and returns an AddCommand with a Deadline task.
     *
     * @param words The command words array.
     * @return An AddCommand with a Deadline task.
     * @throws NikoException If the deadline format is invalid.
     */
    private static Command parseDeadline(String[] words) throws NikoException {
        if (words.length < 2) {
            throw new NikoException("Please provide both a task and a deadline.");
        }
        String[] parts = words[1].split(" /by ");
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new NikoException("Please ensure the format is: 'deadline [task] /by [date]'.");
        }
        return new AddCommand(new Deadline(parts[0].trim(), parts[1].trim()));
    }

    /**
     * Parses the 'event' command and returns an AddCommand with an Event task.
     *
     * @param words The command words array.
     * @return An AddCommand with an Event task.
     * @throws NikoException If the event format is invalid.
     */
    private static Command parseEvent(String[] words) throws NikoException {
        if (words.length < 2) {
            throw new NikoException("Please provide both event details and time.");
        }
        String[] eventParts = words[1].split(" /from | /to ");
        if (eventParts.length < 3 || eventParts[0].trim().isEmpty() || eventParts[1].trim().isEmpty() || eventParts[2].trim().isEmpty()) {
            throw new NikoException("Please ensure the format is: 'event [description] /from [start time] /to [end time]'.");
        }
        return new AddCommand(new Event(eventParts[0].trim(), eventParts[1].trim(), eventParts[2].trim()));
    }

    /**
     * Parses the 'delete' command and returns a DeleteCommand.
     *
     * @param words The command words array.
     * @return A DeleteCommand with the task index.
     * @throws NikoException If the task index is invalid.
     */
    private static Command parseDelete(String[] words) throws NikoException {
        try {
            int taskIndex = Integer.parseInt(words[1].trim());
            return new DeleteCommand(taskIndex);
        } catch (Exception e) {
            throw new NikoException("Please provide a valid task number.");
        }
    }

    /**
     * Parses the 'mark' command and returns a MarkCommand.
     *
     * @param words The command words array.
     * @return A MarkCommand with the task index.
     * @throws NikoException If the task index is invalid.
     */
    private static Command parseMark(String[] words) throws NikoException {
        try {
            int taskIndex = Integer.parseInt(words[1].trim());
            return new MarkCommand(taskIndex);
        } catch (Exception e) {
            throw new NikoException("Please provide a valid task number.");
        }
    }

    /**
     * Parses the 'unmark' command and returns an UnmarkCommand.
     *
     * @param words The command words array.
     * @return An UnmarkCommand with the task index.
     * @throws NikoException If the task index is invalid.
     */
    private static Command parseUnmark(String[] words) throws NikoException {
        try {
            int taskIndex = Integer.parseInt(words[1].trim());
            return new UnmarkCommand(taskIndex);
        } catch (Exception e) {
            throw new NikoException("Please provide a valid task number.");
        }
    }

    /**
     * Parses the 'search' command and returns a SearchCommand.
     *
     * @param words The command words array.
     * @return A SearchCommand with the search query.
     * @throws NikoException If the search query is empty.
     */
    private static Command parseSearch(String[] words) throws NikoException {
        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new NikoException("Please provide a search query.");
        }
        return new SearchCommand(words[1].trim());
    }

    /**
     * Parses the 'find' command and returns a FindCommand.
     *
     * @param words The command words array.
     * @return A FindCommand with the search term.
     * @throws NikoException If the search term is empty.
     */
    private static Command parseFind(String[] words) throws NikoException {
        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new NikoException("Please provide a search term to find.");
        }
        return new FindCommand(words[1].trim());
    }
}