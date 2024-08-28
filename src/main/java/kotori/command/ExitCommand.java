package kotori.command;

import kotori.taskList.TaskList;
import static kotori.Ui.Ui.printExit;
public class ExitCommand extends Command{
    TaskList list;

    public ExitCommand(TaskList list) {
        this.list = list;
    }
    public void execute() {
       printExit();
   }
}
