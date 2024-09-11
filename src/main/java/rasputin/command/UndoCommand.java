package rasputin.command;

import rasputin.gui.Ui;
import rasputin.task.InvalidTaskException;
import rasputin.task.RasputinException;
import rasputin.task.TaskList;

public class UndoCommand extends Command {
    protected TaskList tasks;

    public UndoCommand(TaskList tasks) {
        this.tasks = tasks;
    }

    @Override
    public String execute() throws RasputinException {
        Undoable lastCommand = tasks.removeLastCommand();
        if (lastCommand == null) {
            throw new InvalidTaskException("ERROR! No command to undo!");
        }
        return lastCommand.undo();
    }

    @Override
    public boolean isTerminated() {
        return false;
    }
}
