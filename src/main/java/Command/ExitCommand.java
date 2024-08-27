package Command;

import Storage.Storage;
import TaskList.TaskList;
import static Ui.Ui.printExit;
public class ExitCommand extends Command{
    TaskList list;

    public ExitCommand(TaskList list) {
        this.list = list;
    }
    public void execute() {
       printExit();
   }
}
