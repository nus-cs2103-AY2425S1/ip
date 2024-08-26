package wolfie.command;

import java.io.IOException;

import wolfie.exception.WolfieException;
import wolfie.task.TaskList;
import wolfie.util.Storage;
import wolfie.util.Ui;

/**
 * Represents a command that can be executed by the user.
 */
public abstract class Command {
    /**
     * Executes the command.
     * @param tasks The list of tasks
     * @param ui The user interface
     * @param storage The storage object
     * @throws IOException If an I/O error occurs
     * @throws WolfieException If an error occurs while executing the command
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, WolfieException;
    /**
     * Returns true if the program should exit after executing the command.
     * @return true if the program should exit after executing the command, false otherwise
     */
    public boolean isExit() {
        return false;
    } // By default, the program will not exit
}
