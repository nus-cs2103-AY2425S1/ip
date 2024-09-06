package LittleMissHelpful.Command;

import LittleMissHelpful.Storage;
import LittleMissHelpful.TaskList;
import LittleMissHelpful.Ui;
import LittleMissHelpful.Exception.InvalidCommandException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException;
        /**
         * Executes command given tasks, ui, and storage
         */
    

    public abstract boolean isExit();
        /**
         * Returns true if is ExitCommand, else false
         */
}
