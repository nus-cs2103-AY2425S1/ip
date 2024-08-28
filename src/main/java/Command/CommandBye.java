package command;

/* My import */
import blitz.Storage;
import blitz.TaskList;
import blitz.Ui;
import exception.BlitzException;

public class CommandBye extends Command {
    public CommandBye(String command) {
        super(command);
    }

    public void execute(TaskList list, Ui ui, Storage storage) throws BlitzException {
        /* Do nothing */
    }

}
