package sigma.command;

import sigma.exception.SigmaException;
import sigma.utils.Storage;
import sigma.utils.TaskList;
import sigma.utils.Ui;

/**
 * Represents the command to execute the user's input.
 */
public class UnrecognisedCommand extends Command {

    public UnrecognisedCommand(String[] commandArray) {
        super(commandArray);
    }

    /**
     * Throws an unrecognised error.
     *
     * @param tasks
     * @param ui
     * @param storage
     * @throws SigmaException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws SigmaException {
        Ui.throwUnrecognisedError();
        return "???";
    }

}
