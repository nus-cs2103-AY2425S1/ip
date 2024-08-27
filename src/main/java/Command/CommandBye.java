package Command;

/* My import */
import Blitz.Storage;
import Blitz.TaskList;
import Blitz.Ui;
import Exception.BlitzException;

public class CommandBye extends Command {
    public CommandBye(String command) {
        super(command);
    }

    public void execute(TaskList list, Ui ui, Storage storage) throws BlitzException {
        /* Do nothing */
    }

}
