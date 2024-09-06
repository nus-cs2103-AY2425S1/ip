package bob.Command;

import bob.Storage.Storage;
import bob.Tasks.Task;
import bob.Ui.Ui;

import java.util.ArrayList;


public class ExitCommand extends Command {

    @Override
    public String execute(ArrayList<Task> tasks, Storage storage, Ui ui) {

        return ui.showGoodbye();
    }

}

