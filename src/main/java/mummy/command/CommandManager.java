package mummy.command;

import java.util.EmptyStackException;
import java.util.Stack;

import mummy.task.TaskList;
import mummy.ui.MummyException;
import mummy.utility.Storage;

/**
 * Manages the execution, undoing, and redoing of commands.
 * The CommandManager maintains two stacks: one for commands that have been executed
 * (backwardStack) and one for commands that have been undone (forwardStack). This allows
 * for commands to be undone and redone as needed.
 */
public class CommandManager {
    private final Stack<Command> backwardStack = new Stack<>();

    private final Stack<Command> forwardStack = new Stack<>();

    private final TaskList taskList;

    private final Storage storage;

    /**
     * Constructs a CommandManager with the specified TaskList and Storage.
     *
     * @param taskList the TaskList to be managed by this CommandManager
     * @param storage the Storage to be used by this CommandManager
     */
    public CommandManager(TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Retrieves the most recent command.
     *
     * @return The most recent {@code Command} object
     * @throws MummyException If there are no recent commands in the stack
     */
    public Command getMostRecentCommand() throws MummyException {
        try {
            return backwardStack.peek();
        } catch (EmptyStackException exception) {
            throw new MummyException("No recent command");
        }
    }

    /**
     * Executes the given command.
     *
     * @param command The command to be executed.
     * @return The result of the command execution as a String.
     * @throws MummyException If an error occurs during command execution.
     */
    public String execute(Command command) throws MummyException {
        switch (command.getCommandType()) {
        case UNDO:
            return undo(command);
        case REDO:
            return redo(command);
        default:
            saveCommand(command);
            return command.execute(this.taskList, this.storage);
        }
    }

    private void saveCommand(Command command) {
        backwardStack.push(command);
        forwardStack.clear();
    }

    private String undo(Command undoCommand) throws MummyException {
        assert undoCommand.getCommandType().equals(Command.CommandType.UNDO);

        if (backwardStack.isEmpty()) {
            throw new MummyException("No command to undo.");
        }

        Command command = backwardStack.pop();

        String message = String.format(
                "%s\n%s",
                undoCommand.execute(this.taskList, this.storage),
                command.undo(this.taskList, this.storage)
        );

        forwardStack.push(command);
        return message;
    }

    private String redo(Command redoCommand) throws MummyException {
        assert redoCommand.getCommandType().equals(Command.CommandType.REDO);

        if (forwardStack.isEmpty()) {
            throw new MummyException("No command to redo.");
        }

        Command command = forwardStack.pop();

        String message = String.format(
                "%s\n%s",
                redoCommand.execute(this.taskList, this.storage),
                command.execute(this.taskList, this.storage)
        );

        backwardStack.push(command);
        return message;
    }
}
