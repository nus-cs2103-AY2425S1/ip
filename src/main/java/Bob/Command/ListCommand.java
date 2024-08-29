package Bob.Command;

import Bob.Storage.Storage;
import Bob.Tasks.Task;
import Bob.Ui.Ui;

import java.util.ArrayList;


public class ListCommand implements Command {

    @Override
    public void execute(ArrayList<Task> tasks, Storage storage, Ui ui) {
        ui.showTaskList(tasks);
    }
}
