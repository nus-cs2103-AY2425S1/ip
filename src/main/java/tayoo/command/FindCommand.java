package tayoo.command;

import tayoo.Storage;
import tayoo.Tasklist;
import tayoo.Ui;
import tayoo.exception.TayooException;

public class FindCommand extends Command {

    private final String toSearch;

    public FindCommand(String input) {
        this.toSearch = input;
    };
    @Override
    public void execute(Tasklist tasklist, Ui ui, Storage storage) throws TayooException {
        ui.printText(tasklist.find(toSearch));
    }

    @Override
    public String guiExecute(Tasklist tasklist, Ui ui, Storage storage) throws TayooException {
        return tasklist.find(toSearch);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
