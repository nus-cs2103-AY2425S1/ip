package atlas.commands;

import atlas.exceptions.AtlasException;
import atlas.utils.DateTime;
import atlas.utils.Util;

/**
 * Represents the CommandManager class which returns the command that is to be executed.
 */
public class CommandManager {
    /**
     * @return ExitCommand The command that exits the user out of the chatbot when executed.
     */
    public static ExitCommand getExitCommand() {
        return new ExitCommand();
    }

    /**
     * @param commandsArray The array containing the command words the user has typed in.
     * @return ListCommand The command that lists all the tasks.
     * @throws AtlasException The exception to be thrown in the event of any poorly typed command.
     */
    public static ListCommand getListCommand(String[] commandsArray) throws AtlasException {
        if (commandsArray.length > 1) {
            throw new AtlasException("List command should not be called with any additional arguments.");
        }

        return new ListCommand();
    }

    /**
     * @param commandsArray The array containing the command words the user has typed in.
     * @return MarkCommand The command that marks the task as done.
     * @throws AtlasException The exception to be thrown in the event of any poorly typed command.
     */
    public static MarkCommand getMarkCommand(String[] commandsArray) throws AtlasException {
        if (commandsArray.length == 1) {
            throw new AtlasException("Marking a task as done requires the task number.");
        } else if (Util.isNotNumber(commandsArray[1])) {
            throw new AtlasException("Task number provided is not a number.");
        } else if (commandsArray.length > 2) {
            throw new AtlasException("Marking a task as done only requires the task number.");
        }

        int markIndex = Integer.parseInt(commandsArray[1]) - 1;
        return new MarkCommand(markIndex);
    }

    /**
     * @param commandsArray The array containing the command words the user has typed in.
     * @return UnmarkCommand The command that unmarks the task as not done.
     * @throws AtlasException The exception to be thrown in the event of any poorly typed command.
     */
    public static UnmarkCommand getUnmarkCommand(String[] commandsArray) throws AtlasException {
        if (commandsArray.length == 1) {
            throw new AtlasException("Unmarking a task as undone requires the task number.");
        } else if (Util.isNotNumber(commandsArray[1])) {
            throw new AtlasException("Task number provided is not a number.");
        } else if (commandsArray.length > 2) {
            throw new AtlasException("Unmarking a task as undone only requires the task number.");
        }

        int unmarkIndex = Integer.parseInt(commandsArray[1]) - 1;
        return new UnmarkCommand(unmarkIndex);
    }

    /**
     * @param fullCommand The command string the user has typed in.
     * @return TodoCommand The command that creates a new Todo.
     * @throws AtlasException The exception to be thrown in the event of any poorly typed command.
     */
    public static TodoCommand getTodoCommand(String fullCommand) throws AtlasException {
        String[] commandsArray;
        commandsArray = fullCommand.split("todo ");
        if (commandsArray.length == 1) {
            throw new AtlasException("The description of a todo cannot be empty.");
        }

        return new TodoCommand(commandsArray[1]);
    }

    /**
     * @param fullCommand The command string the user has typed in.
     * @return DeadlineCommand The command that creates a new Deadline.
     * @throws AtlasException The exception to be thrown in the event of any poorly typed command.
     */
    public static DeadlineCommand getDeadlineCommand(String fullCommand) throws AtlasException {
        String[] commandsArray;
        commandsArray = fullCommand.split("deadline | /by ");
        if (commandsArray.length == 1) {
            throw new AtlasException("The description of a deadline cannot be empty.");
        } else if (commandsArray.length < 3) {
            throw new AtlasException("A deadline requires a description and a date, in that order.");
        }

        return new DeadlineCommand(commandsArray[1], DateTime.formatDate(commandsArray[2]));
    }

    /**
     * @param fullCommand The command string the user has typed in.
     * @return EventCommand The command that creates a new Event.
     * @throws AtlasException The exception to be thrown in the event of any poorly typed command.
     */
    public static EventCommand getEventCommand(String fullCommand) throws AtlasException {
        String[] commandsArray;
        commandsArray = fullCommand.split("event | /from | /to ");
        if (commandsArray.length == 1) {
            throw new AtlasException("The description of an event cannot be empty.");
        } else if (commandsArray.length < 4) {
            throw new AtlasException("An event requires a description, start and end times, in that order.");
        }

        return new EventCommand(commandsArray[1],
                DateTime.formatDate(commandsArray[2]),
                DateTime.formatDate(commandsArray[3]));
    }

    /**
     * @param commandsArray The array containing the command words the user has typed in.
     * @return DeleteCommand The command that deletes the task from the list.
     * @throws AtlasException The exception to be thrown in the event of any poorly typed command.
     */
    public static DeleteCommand getDeleteCommand(String[] commandsArray) throws AtlasException {
        if (commandsArray.length == 1) {
            throw new AtlasException("Deleting a task as undone requires the task number.");
        } else if (Util.isNotNumber(commandsArray[1])) {
            throw new AtlasException("Task number provided is not a number.");
        } else if (commandsArray.length > 2) {
            throw new AtlasException("Deleting a task only requires the task number.");
        }

        int deleteIndex = Integer.parseInt(commandsArray[1]) - 1;
        return new DeleteCommand(deleteIndex);
    }

    /**
     * @param fullCommand The command string the user has typed in.
     * @return FindCommand The command that finds the tasks that match the pattern.
     * @throws AtlasException The exception to be thrown in the event of any poorly typed command.
     */
    public static FindCommand getFindCommand(String fullCommand) throws AtlasException {
        String[] commandsArray;
        commandsArray = fullCommand.split("find ");
        if (commandsArray.length == 1) {
            throw new AtlasException("Finding tasks requires the task description.");
        } else if (commandsArray.length > 2) {
            throw new AtlasException("Invalid task description.");
        }

        return new FindCommand(commandsArray[1]);
    }
}
