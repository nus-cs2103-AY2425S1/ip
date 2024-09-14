package bangmang.command;

import bangmang.storage.Storage;
import bangmang.tasks.TaskList;
import bangmang.ui.Ui;
import bangmang.exception.InvalidCommandException;

public abstract class Command {
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException;
        /**
         * Executes command given tasks, ui, and storage
         */
    

    public abstract boolean isExit();
        /**
         * Returns true if is ExitCommand, else false
         */
}
