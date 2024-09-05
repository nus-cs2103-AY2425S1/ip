package froggy;

/**
 * Exits from Froggy program by setting IsExit() as true.
 */
public class ExitCommand extends Command {

    public ExitCommand() {

    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {

    }

    @Override
    public boolean isExit() {
        return true;
    }
}
