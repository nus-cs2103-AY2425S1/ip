package struggling.command;

import java.io.IOException;

import struggling.Storage;
import struggling.TaskList;
import struggling.Ui;

/**
 * Command object specifies if the program should be terminated
 * and a set of actions for Ui, Storage and TaskList.
 */
public abstract class Command {

    /**
     * Execute operation specified by Command Object
     * with TaskList, Ui and Storage provided.
     *
     * @param tasks   Class to work with a collection of tasks.
     * @param ui      Class to interact with user.
     * @param storage Class to load/save to file.
     * @throws IOException If data saved is corrupted while exiting.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException;

    /**
     * Returns true/false if the program is terminated.
     */
    public boolean isExit() {
        return false;
    }
}
