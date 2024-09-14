package tayoo.command;

import tayoo.Storage;
import tayoo.Tasklist;
import tayoo.Ui;
import tayoo.exception.TayooException;

public class ExitCommand extends Command {

    public ExitCommand() {
    }

    @Override
    public void execute(Tasklist tasklist, Ui ui, Storage storage) throws TayooException {
        ui.showExit();
    }

    @Override
    public String guiExecute(Tasklist tasklist, Ui ui, Storage storage) throws TayooException {
        return ui.getExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
