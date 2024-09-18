package mummy.command;

import java.util.EmptyStackException;
import java.util.Stack;

import mummy.task.TaskList;
import mummy.ui.MummyException;
import mummy.utility.Storage;

public class CommandManager {
    private final Stack<Command> backwardStack = new Stack<>();

    private final Stack<Command> forwardStack = new Stack<>();

    private final TaskList taskList;

    private final Storage storage;

    public CommandManager(TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }

    public Command getMostRecentCommand() throws MummyException {
        try {
            return backwardStack.peek();
        } catch (EmptyStackException exception) {
            throw new MummyException("No recent command");
        }
    }

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
