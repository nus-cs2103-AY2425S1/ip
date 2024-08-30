package atlas.parser;

import java.time.DateTimeException;

import atlas.commands.AllCommand;
import atlas.commands.Command;
import atlas.commands.DeadlineCommand;
import atlas.commands.DeleteCommand;
import atlas.commands.EventCommand;
import atlas.commands.ExitCommand;
import atlas.commands.FindCommand;
import atlas.commands.ListCommand;
import atlas.commands.MarkCommand;
import atlas.commands.TodoCommand;
import atlas.commands.UnmarkCommand;
import atlas.exceptions.AtlasException;
import atlas.utils.DateTime;

/**
 * Parser class which contain methods to parse the command typed into the chatbot by the user.
 */
public class Parser {
    /**
     * Parses the command typed into the chatbot by the user.
     *
     * @param fullCommand The full command a user types into the chatbot through the command line.
     * @return Command The command to be executed by the chatbot.
     * @throws AtlasException The exception to be thrown in the event of any error.
     */
    public static Command parse(String fullCommand) throws AtlasException {
        String[] commandsArray = fullCommand.split(" ");
        String command = commandsArray[0].toUpperCase();
        try {
            switch (AllCommand.valueOf(command)) {
            case BYE:
                return new ExitCommand();
            case LIST:
                if (commandsArray.length > 1) {
                    throw new AtlasException("List command should not be called with any additional arguments.");
                }

                return new ListCommand();
            case MARK:
                if (commandsArray.length == 1) {
                    throw new AtlasException("Marking a task as done requires the task number.");
                } else if (isNotNumber(commandsArray[1])) {
                    throw new AtlasException("Task number provided is not a number.");
                } else if (commandsArray.length > 2) {
                    throw new AtlasException("Marking a task as done only requires the task number.");
                }

                int markIndex = Integer.parseInt(commandsArray[1]) - 1;
                return new MarkCommand(markIndex);
            case UNMARK:
                if (commandsArray.length == 1) {
                    throw new AtlasException("Unmarking a task as undone requires the task number.");
                } else if (isNotNumber(commandsArray[1])) {
                    throw new AtlasException("Task number provided is not a number.");
                } else if (commandsArray.length > 2) {
                    throw new AtlasException("Unmarking a task as undone only requires the task number.");
                }

                int unmarkIndex = Integer.parseInt(commandsArray[1]) - 1;
                return new UnmarkCommand(unmarkIndex);
            case TODO:
                commandsArray = fullCommand.split("todo ");
                if (commandsArray.length == 1) {
                    throw new AtlasException("The description of a todo cannot be empty.");
                }

                return new TodoCommand(commandsArray[1]);
            case DEADLINE:
                commandsArray = fullCommand.split("deadline | /by ");
                if (commandsArray.length == 1) {
                    throw new AtlasException("The description of a deadline cannot be empty.");
                } else if (commandsArray.length < 3) {
                    throw new AtlasException("A deadline requires a description and a date, in that order.");
                }

                return new DeadlineCommand(commandsArray[1], DateTime.formatDate(commandsArray[2]));
            case EVENT:
                commandsArray = fullCommand.split("event | /from | /to ");
                if (commandsArray.length == 1) {
                    throw new AtlasException("The description of an event cannot be empty.");
                } else if (commandsArray.length < 4) {
                    throw new AtlasException("An event requires a description, start and end times, in that order.");
                }

                return new EventCommand(commandsArray[1],
                        DateTime.formatDate(commandsArray[2]),
                        DateTime.formatDate(commandsArray[3]));
            case DELETE:
                if (commandsArray.length == 1) {
                    throw new AtlasException("Deleting a task as undone requires the task number.");
                } else if (isNotNumber(commandsArray[1])) {
                    throw new AtlasException("Task number provided is not a number.");
                } else if (commandsArray.length > 2) {
                    throw new AtlasException("Deleting a task only requires the task number.");
                }

                int deleteIndex = Integer.parseInt(commandsArray[1]) - 1;
                return new DeleteCommand(deleteIndex);

            case FIND:
                commandsArray = fullCommand.split("find ");
                if (commandsArray.length == 1) {
                    throw new AtlasException("Finding tasks requires the task description.");
                } else if (commandsArray.length > 2) {
                    throw new AtlasException("Invalid task description.");
                }

                return new FindCommand(commandsArray[1]);

            default:
                throw new AtlasException("Unknown command.");
            }
        } catch (IllegalArgumentException e) {
            throw new AtlasException("Unknown command.");
        } catch (DateTimeException e) {
            throw new AtlasException("Invalid DateTime format. Please use yyyy-mm-dd HHmm");
        }
    }

    /**
     * Checks if the string provided is not a number.
     *
     * @param s The string to be checked.
     * @return boolean The boolean value of whether the string is not a number.
     */
    public static boolean isNotNumber(String s) {
        try {
            Integer.parseInt(s);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }
}
