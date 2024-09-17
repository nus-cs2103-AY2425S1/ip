package samson;

import samson.command.Command;
import samson.command.ExitCommand;
import samson.command.ListCommand;
import samson.command.MarkCommand;
import samson.command.UnmarkCommand;
import samson.command.DeleteCommand;
import samson.command.AddCommand;
import samson.command.FindCommand;

import samson.task.Deadline;
import samson.task.Event;
import samson.task.ToDo;

/**
 * The Parser class is responsible for interpreting user input and converting
 * it into executable commands. It parses the input string and returns the corresponding
 * <code>Command</code> object, which can be executed to perform the desired action.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding <code>Command</code> object.
     * Based on the command word entered by the user, it directs to the relevant helper method.
     *
     * @param userInput The input string entered by the user.
     * @return The Command object that corresponds to the user's input.
     * @throws SamException If the input is invalid or incomplete.
     */
    public static Command parse(String userInput) throws SamException {
        String[] words = userInput.split(" ", 2);
        String commandWord = words[0];

        switch (commandWord) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
                return parseMarkCommand(words);
            case "unmark":
                return parseUnmarkCommand(words);
            case "delete":
                return parseDeleteCommand(words);
            case "todo":
                return parseTodoCommand(words);
            case "deadline":
                return parseDeadlineCommand(words);
            case "event":
                return parseEventCommand(words);
            case "find":
                return parseFindCommand(words);
            default:
                throw new SamException("I'm sorry, but I don't know what that means :-( \n " +
                        "Kindly provide the tasks starting with 'todo', 'event', 'deadline'!!");
        }
    }

    /**
     * Parses the "mark" command and returns a <code>MarkCommand</code> object.
     * This command marks a task as complete by its index in the task list.
     *
     * @param words The split input string containing the command and its parameters.
     * @return The MarkCommand object.
     * @throws SamException If no task number is provided or if the task number is invalid.
     */
    private static Command parseMarkCommand(String[] words) throws SamException {
        if (words.length < 2) {
            throw new SamException("Please specify the task number to mark.");
        }
        try {
            int markIndex = Integer.parseInt(words[1].trim());
            return new MarkCommand(markIndex);
        } catch (NumberFormatException e) {
            throw new SamException("Please provide a valid task number to mark.");
        }
    }

    /**
     * Parses the "unmark" command and returns an <code>UnmarkCommand</code> object.
     * This command marks a task as not complete by its index in the task list.
     *
     * @param words The split input string containing the command and its parameters.
     * @return The UnmarkCommand object.
     * @throws SamException If no task number is provided or if the task number is invalid.
     */
    private static Command parseUnmarkCommand(String[] words) throws SamException {
        if (words.length < 2) {
            throw new SamException("Please specify the task number to unmark.");
        }
        try {
            int unmarkIndex = Integer.parseInt(words[1].trim());
            return new UnmarkCommand(unmarkIndex);
        } catch (NumberFormatException e) {
            throw new SamException("Please provide a valid task number to unmark.");
        }
    }

    /**
     * Parses the "delete" command and returns a <code>DeleteCommand</code> object.
     * This command deletes a task by its index in the task list.
     *
     * @param words The split input string containing the command and its parameters.
     * @return The DeleteCommand object.
     * @throws SamException If no task number is provided or if the task number is invalid.
     */
    private static Command parseDeleteCommand(String[] words) throws SamException {
        if (words.length < 2) {
            throw new SamException("Please specify the task number to delete.");
        }
        try {
            int deleteIndex = Integer.parseInt(words[1].trim());
            return new DeleteCommand(deleteIndex);
        } catch (NumberFormatException e) {
            throw new SamException("Please provide a valid task number to delete.");
        }
    }

    /**
     * Parses the "todo" command and returns an <code>AddCommand</code> object to add a <code>ToDo</code> task.
     * This command adds a new ToDo task to the task list.
     *
     * @param words The split input string containing the command and its parameters.
     * @return The AddCommand object for a ToDo task.
     * @throws SamException If no task description is provided.
     */
    private static Command parseTodoCommand(String[] words) throws SamException {
        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new SamException("Please provide your description!!!! \n" +
                    "Example call: todo your_task");
        }
        return new AddCommand(new ToDo(words[1].trim()));
    }

    /**
     * Parses the "deadline" command and returns an <code>AddCommand</code> object to add a <code>Deadline</code> task.
     * This command adds a new Deadline task with a due date to the task list.
     *
     * @param words The split input string containing the command and its parameters.
     * @return The AddCommand object for a Deadline task.
     * @throws SamException If no description or deadline date is provided, or if the date format is invalid.
     */
    private static Command parseDeadlineCommand(String[] words) throws SamException {
        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new SamException("Please provide the description and deadline of your task \n " +
                    "Example call: deadline your_task /by yyyy-MM-dd HHmm");
        }
        String[] deadlineParts = words[1].split(" /by ");
        if (deadlineParts.length < 2) {
            throw new SamException("Please provide the deadline using /by.");
        }
        String deadlineDescription = deadlineParts[0].trim();
        String by = deadlineParts[1].trim();
        return new AddCommand(new Deadline(deadlineDescription, by));
    }

    /**
     * Parses the "event" command and returns an <code>AddCommand</code> object to add an <code>Event</code> task.
     * This command adds a new Event task with a start and end time to the task list.
     *
     * @param words The split input string containing the command and its parameters.
     * @return The AddCommand object for an Event task.
     * @throws SamException If no description, start time, or end time is provided, or if the date format is invalid.
     */
    private static Command parseEventCommand(String[] words) throws SamException {
        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new SamException("The description, start, and end time of an event cannot be empty. \n " +
                    "Example call: event your_event /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm");
        }
        String[] eventParts = words[1].split(" /from | /to ");
        if (eventParts.length < 3) {
            throw new SamException("Please provide the event timings using /from and /to.");
        }
        String eventDescription = eventParts[0].trim();
        String from = eventParts[1].trim();
        String to = eventParts[2].trim();
        return new AddCommand(new Event(eventDescription, from, to));
    }

    /**
     * Parses the "find" command and returns a <code>FindCommand</code> object to search for tasks.
     * This command searches for tasks containing the provided keyword in the task list.
     *
     * @param words The split input string containing the command and its parameters.
     * @return The FindCommand object for searching tasks.
     * @throws SamException If no keyword is provided for the search.
     */
    private static Command parseFindCommand(String[] words) throws SamException {
        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new SamException("Please provide a keyword to search for. \n" +
                    "Example call: find your_task ");
        }
        return new FindCommand(words[1].trim());
    }
}
