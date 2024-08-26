package Command;

import Storage.Storage;
import TaskList.TaskList;
import static Ui.Ui.printExit;
public class ExitCommand extends Command{
    Storage storage;
    TaskList list;

    public ExitCommand(Storage storage, TaskList list) {
        this.storage = storage;
        this.list = list;
    }
    public void execute() {
       printExit();
       storage.updateFile(list);
   }
}
