package mummy.command;

import java.io.IOException;
import java.util.HashMap;

import mummy.task.TaskList;
import mummy.ui.MummyException;
import mummy.utility.Storage;

/**
 * Represents an abstract class for commands in the application.
 * Each command is responsible for executing a specific action and
 * can be extended to implement different types of commands.
 */
public abstract class Command {

    private final HashMap<String, String> arguments;

    /**
     * Represents the types of commands that can be executed.
     */
    public enum CommandType {
        BYE, LIST, MARK, UNMARK, ADD, DELETE, FIND,
        UNDO, REDO, UNKNOWN
    }

    /**
     * Constructs a new Command object with the given arguments.
     *
     * @param arguments the arguments for the command
     */
    public Command(HashMap<String, String> arguments) {
        this.arguments = arguments;
    }

    /**
     * Creates a Command object based on the given full command string.
     *
     * @param fullCommand the full command string
     * @return a Command object corresponding to the given command string
     * @throws MummyException if an error occurs during parsing or if the command is unknown
     */
    public static Command of(String fullCommand) throws MummyException {
        HashMap<String, String> arguments = Parser.parse(fullCommand);

        switch (arguments.getOrDefault("command", "")) {
        case "bye":
            return new ByeCommand(arguments);
        case "list":
            return new ListCommand(arguments);
        case "mark":
            return new MarkCommand(arguments);
        case "unmark":
            return new UnmarkCommand(arguments);
        case "todo":
            return new ToDoCommand(arguments);
        case "deadline":
            return new DeadlineCommand(arguments);
        case "event":
            return new EventCommand(arguments);
        case "delete":
            return new DeleteCommand(arguments);
        case "find":
            return new FindCommand(arguments);
        case "undo":
            return new UndoCommand(arguments);
        case "redo":
            return new RedoCommand(arguments);
        default:
            throw new MummyException("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Executes the command.
     *
     * @param taskList the task list to be modified
     * @param storage the storage for saving and loading tasks
     * @return message generated from executing the command
     * @throws MummyException if there is an error executing the command
     */
    public abstract String execute(TaskList taskList, Storage storage) throws MummyException;

    /**
     * Returns a boolean value indicating whether the command is an exit command.
     *
     * @return true if the command is an exit command, false otherwise.
     */
    public abstract boolean isExit();

    /**
     * Returns the type of the command.
     * @return the type of the command
     */
    public abstract CommandType getCommandType();

    /**
     * Undoes this command and updates the given task list and storage if necessary.
     *
     * @param taskList The task list to undo the command on.
     * @param storage The storage to undo the command on.
     * @return A string message indicating the result of the undo operation.
     * @throws MummyException If an error occurs during the undo operation.
     */
    public abstract String undo(TaskList taskList, Storage storage) throws MummyException;

    /**
     * Retrieves the value associated with the specified key from the arguments map.
     *
     * @param key the key whose associated value is to be retrieved
     * @return the value associated with the specified key, or null if the key is not present in the map
     */
    public final String getArgument(String key) {
        return this.arguments.get(key);
    }

    /**
     * If the key is not found, returns the default argument.
     *
     * @param key The key to retrieve the value for.
     * @param defaultArgument The default value to return if the key is not found.
     * @return The value associated with the key, or the default argument if the key is not found.
     */
    public final String getArgument(String key, String defaultArgument) {
        return this.arguments.getOrDefault(key, defaultArgument);
    }

    /**
     * Saves the given task list to the specified storage.
     *
     * @param taskList The task list to be saved.
     * @param storage The storage to save the task list to.
     * @throws MummyException If an error occurs while saving the task list to the storage.
     */
    public void saveTaskListToStorage(TaskList taskList, Storage storage) throws MummyException {
        try {
            storage.save(taskList.toFileRecords());
        } catch (IOException exception) {
            throw new MummyException("Something went wrong when saving to file: "
                    + exception.getMessage());
        }
    }

    private static final class UndoCommand extends Command {
        public UndoCommand(HashMap<String, String> arguments) {
            super(arguments);
        }

        @Override
        public String execute(TaskList taskList, Storage storage) {
            return "Command undone.";
        }

        @Override
        public boolean isExit() {
            return false;
        }

        @Override
        public CommandType getCommandType() {
            return CommandType.UNDO;
        }

        @Override
        public String undo(TaskList taskList, Storage storage) throws MummyException {
            throw new MummyException("Undo command cannot be undone.\n"
                    + "Undo is an irreversible action."
            );
        }
    }

    private static final class RedoCommand extends Command {
        public RedoCommand(HashMap<String, String> arguments) {
            super(arguments);
        }

        @Override
        public String execute(TaskList taskList, Storage storage) {
            return "Command redone.";
        }

        @Override
        public boolean isExit() {
            return false;
        }

        @Override
        public CommandType getCommandType() {
            return CommandType.REDO;
        }

        @Override
        public String undo(TaskList taskList, Storage storage) throws MummyException {
            throw new MummyException("Redo command cannot be undone.\n"
                    + "Undo is an irreversible action."
            );
        }
    }
}
