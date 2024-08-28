package command;

/* My import */
import blitz.Storage;
import blitz.TaskList;
import blitz.Ui;

import exception.BlitzException;

public class CommandBye extends Command {
    /**
     * Constructs a new CommandBye object with specified command String.
     *
     * @param command Command String to be associated with this Command object.
     */
    public CommandBye(String command) {
        super(command);
    }

    public void execute(TaskList list, Ui ui, Storage storage) throws BlitzException {
        /* Do nothing */
    }

}
