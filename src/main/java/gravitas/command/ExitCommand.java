package gravitas.command;

import gravitas.exception.GravitasException;
import gravitas.storage.Storage;
import gravitas.tasklist.TaskList;

/**
 * Represents the command to exit the program.
 */
public class ExitCommand extends Command {

    private static final String BYE = "Bye. Hope to see you again soon!";

    /**
     * Constructor for the ExitCommand class.
     */
    public ExitCommand() {

    }

    /**
     * Executes the command to exit the program.
     */
    @Override
    public String executeCommand(TaskList taskList, Storage storage) throws GravitasException {
        setExit(true);
        return BYE;
    }
}
