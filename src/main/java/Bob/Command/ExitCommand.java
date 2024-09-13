package bob.command;

import bob.storage.Storage;
import bob.tasks.Task;
import bob.ui.Ui;

import java.util.ArrayList;


public class ExitCommand extends Command {

    @Override
    public String execute(ArrayList<Task> tasks, Storage storage, Ui ui) {

        return ui.showGoodbye();
    }

}

