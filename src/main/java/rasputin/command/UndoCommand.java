package rasputin.command;

import rasputin.gui.Ui;
import rasputin.task.InvalidTaskException;
import rasputin.task.RasputinException;
import rasputin.task.TaskList;

public class UndoCommand extends Command {
    protected TaskList tasks;

    /**
     * Constructor for class UndoCommand.
     *
     * @param tasks
     */
    public UndoCommand(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Undoes the most recent undoable Command.
     *
     * @return Rasputin's response as a String
     * @throws RasputinException
     */
    @Override
    public String execute() throws RasputinException {
        Undoable lastCommand = tasks.removeLastCommand();
        if (lastCommand == null) {
            throw new InvalidTaskException("ERROR! No command to undo!");
        }
        return lastCommand.undo();
    }

    /**
     * Always returns false.
     *
     * @return False.
     */
    @Override
    public boolean isTerminated() {
        return false;
    }
}
