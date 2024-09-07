package bobbybot.commands;

import bobbybot.BobbyBotException;
import bobbybot.Storage;
import bobbybot.TaskList;
import bobbybot.ui.Ui;

/**
 * Represents a command in BobbyBot.
 */
public abstract class Command {
    protected Memento memento;

    protected boolean isUndo = false;

    protected boolean isUndoable = true;

    /**
     * Executes the command.
     *
     * @param tasks List of tasks.
     * @param ui User interface.
     * @param storage Storage.
     * @throws BobbyBotException If an error occurs during the execution of a command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws BobbyBotException;

    /**
     * Un-executes the command.
     *
     * @param tasks List of tasks.
     * @param ui User interface.
     * @param storage Storage.
     * @throws BobbyBotException If an error occurs during the un-execution of a command.
     */
    public void unExecute(TaskList tasks, Ui ui, Storage storage) throws BobbyBotException {
        if (memento != null) {
            tasks.copyOver(memento.getTaskList());
            storage.saveTasksToFile(tasks.toArray());
        }
        ui.printUndo();
    }

    /**
     * Check if the command is to undo the previous command.
     * @return True if the command is to undo the previous command, false otherwise.
     */
    public boolean isUndo() {
        return isUndo;
    }

    /**
     * Check if the command is undoable.
     * @return True if the command is undoable, false otherwise.
     */
    public boolean isUndoable() {
        return isUndoable;
    }
}
