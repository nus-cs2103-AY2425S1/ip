package cookie.parser;

import cookie.command.ByeCommand;
import cookie.command.Command;
import cookie.command.DeadlineCommand;
import cookie.command.DeleteCommand;
import cookie.command.EventCommand;
import cookie.command.FindCommand;
import cookie.command.ListCommand;
import cookie.command.MarkCommand;
import cookie.command.ToDoCommand;
import cookie.command.UnmarkCommand;
import cookie.exception.CookieException;

/**
 * Parses user input and converts it into corresponding {@code Command} objects.
 */
public class Parser {
    /**
     * Returns a command to be executed.
     *
     * @param input String to be parsed.
     * @return Command to be executed.
     */
    public String parseCommand(String input) {
        String[] splitInput = input.split(" ", 2);
        return splitInput[0];
    }

    /**
     * Returns the description of a task
     *
     * @param input String to be parsed.
     * @return Description of the task.
     */
    public String parseDescription(String input) {
        String[] splitInput = input.split(" ", 2);
        return (splitInput.length > 1) ? splitInput[1] : "";
    }

    /**
     * Returns the index of task.
     *
     * @param input String to be parsed.
     * @return Index of task.
     */
    public int parseInteger(String input) {
        return Integer.parseInt(input);
    }

    /**
     * Returns the deadline details.
     *
     * @param input String to be parsed.
     * @return Array containing deadline details of deadline task.
     */
    public String[] parseDeadline(String input) {
        return input.split(" /by ", 2);
    }

    /**
     * Returns the event start and end details.
     *
     * @param input String to be parsed.
     * @return Array containing event details of event task.
     */
    public String[] parseEvent(String input) {

        return input.split(" /from | /to ");
    }

    /**
     * Parses the user's input string and converts it into a corresponding {@code Command} object.
     * The method identifies the command type (e.g., "bye", "list", "find", "delete", "mark", etc.)
     * and processes any additional details like task descriptions, deadlines, or event times,
     * converting the input into specific command objects. Throws a {@code CookieException} for
     * invalid inputs or missing required details.
     *
     * @param input the input string entered by the user, which represents a command and its arguments
     * @return the {@code Command} object corresponding to the user's input
     * @throws CookieException if the input command is invalid, unrecognized, or missing required arguments
     */
    public Command parseInputToCommand(String input) throws CookieException {
        String command = parseCommand(input);
        String description = parseDescription(input);

        switch(command) {
        case "bye":
            return new ByeCommand();

        case "list":
            return new ListCommand();

        case "find":
            if (description.isEmpty()) {
                throw new CookieException("Cookie does not know which task to find. \n "
                        + "(Please enter a word after \"find\")");
            }
            return new FindCommand(description);

        case "delete":
            if (description.isEmpty()) {
                throw new CookieException("Cookie does not know which task to delete.\n"
                        + "(Please enter an integer after \"delete\")");
            }

            int deleteIndex = parseInteger(description);

            return new DeleteCommand(deleteIndex);

        case "mark":
            if (description.isEmpty()) {
                throw new CookieException("Cookie does not know which task to mark.\n "
                        + "(Please enter an integer after \"mark\")");
            }
            int markIndex = parseInteger(description);
            return new MarkCommand(markIndex);

        case "unmark":
            if (description.isEmpty()) {
                throw new CookieException("Cookie does not know which task to mark.\n "
                        + "(Please enter an integer after \"mark\")");
            }
            int unmarkIndex = parseInteger(description);
            return new UnmarkCommand(unmarkIndex);

        case "todo":
            if (description.isEmpty()) {
                throw new CookieException("Please enter a task for you to do.");
            }

            return new ToDoCommand(description);

        case "deadline":
            String[] deadlineDetails = parseDeadline(description);
            if (deadlineDetails.length < 2 || deadlineDetails[0].isEmpty() || deadlineDetails[1].isEmpty()) {
                throw new CookieException("Deadlines must include a task todo and a due date \n"
                        + "[task] /by [deadline]");
            }

            return new DeadlineCommand(deadlineDetails[0], deadlineDetails[1]);

        case "event":
            String[] eventDetails = parseEvent(description);

            if (eventDetails.length < 2 || eventDetails[0].isEmpty()
                    || eventDetails[1].isEmpty() || eventDetails[2].isEmpty()) {
                throw new CookieException("Events must include a task, a start time and an end time \n"
                        + "[task] /from [start] /to [end]");
            }

            return new EventCommand(eventDetails[0], eventDetails[1], eventDetails[2]);

        default:
            throw new CookieException("Cookie does not understand this command. I'm sorry!!");
        }
    }
}
