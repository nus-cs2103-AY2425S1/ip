package commands;

import storage.Storage;
import taskList.TaskList;
import ui.Ui;

/**
 * Encapsulates relevant information behind a user command and abstracts the required state changes from the user.
 */
public abstract class Command {
    private final boolean isExitCommand;

    public Command() {
        isExitCommand = false;
    }

    public Command(boolean isExitCommand) {
        this.isExitCommand = isExitCommand;
    }

    // The breaking of the abstraction barrier here is unavoidable as the command must terminate the entire program.
    public boolean isExit() {
        return isExitCommand;
    }

    /**
     * Carry out the required actions of the command, including UI input, task list updating, and storage.
     * Unifying under a single method allows for abstraction of the effects of the command from the caller.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
}
