package jeriel.command;

import jeriel.util.*;
import java.io.IOException;

public abstract class Command {

    /**
     * Executes the command and returns the result as a string.
     *
     * @param tasks   The task list to operate on
     * @param ui      The user interface to show messages
     * @param storage The storage system for saving data
     * @return The result of the execution as a string for the GUI
     * @throws JerielException if the command cannot be executed properly
     * @throws IOException if there is an error during execution
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws JerielException, IOException;

    /**
     * Determines whether the command is an exit command.
     *
     * @return true if this command causes the program to exit
     */
    public boolean isExit() {
        return false;  // Default is false, can be overridden in ExitCommand
    }
}
