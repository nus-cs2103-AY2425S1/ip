package atlas.commands;

import java.time.LocalDateTime;
import java.util.HashMap;

import atlas.exceptions.AtlasException;
import atlas.utils.DateTime;
import atlas.utils.Util;

/**
 * Represents the CommandManager class which returns the command that is to be executed.
 */
public class CommandManager {
    private static final HashMap<AtlasSimpleCommand, AtlasCommand> COMMAND_HASH_MAP = new HashMap<>();

    /**
     * Initializes the hashmap containing the mappings from the short commands to the full commands.
     */
    public static void init() {
        COMMAND_HASH_MAP.put(AtlasSimpleCommand.B, AtlasCommand.BYE);
        COMMAND_HASH_MAP.put(AtlasSimpleCommand.L, AtlasCommand.LIST);
        COMMAND_HASH_MAP.put(AtlasSimpleCommand.M, AtlasCommand.MARK);
        COMMAND_HASH_MAP.put(AtlasSimpleCommand.U, AtlasCommand.UNMARK);
        COMMAND_HASH_MAP.put(AtlasSimpleCommand.R, AtlasCommand.DELETE);
        COMMAND_HASH_MAP.put(AtlasSimpleCommand.F, AtlasCommand.FIND);
        COMMAND_HASH_MAP.put(AtlasSimpleCommand.T, AtlasCommand.TODO);
        COMMAND_HASH_MAP.put(AtlasSimpleCommand.D, AtlasCommand.DEADLINE);
        COMMAND_HASH_MAP.put(AtlasSimpleCommand.E, AtlasCommand.EVENT);
        COMMAND_HASH_MAP.put(AtlasSimpleCommand.H, AtlasCommand.HELP);
    }

    public static AtlasCommand getFullCommandFromSimpleCommand(AtlasSimpleCommand command) {
        return COMMAND_HASH_MAP.get(command);
    }

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

        // Solution below adapted from
        // https://github.com/woke02/ip/blob/master/src/main/java/chatsy/commands/EventCommand.java
        // Check that the start time for an event is before the end time
        LocalDateTime startTime = DateTime.formatDate(commandsArray[1]);
        LocalDateTime endTime = DateTime.formatDate(commandsArray[2]);
        if (startTime.isAfter(endTime) || startTime.isEqual(endTime)) {
            throw new AtlasException("Event start time must be before end time.");
        }

        return new EventCommand(commandsArray[1],
                DateTime.formatDate(commandsArray[2]),
                DateTime.formatDate(commandsArray[3]));
    }

    /**
     * @return HelpCommand The command that displays all the possible commands in the Atlas chatbot.
     */
    public static HelpCommand getHelpCommand() {
        return new HelpCommand();
    }
}
