package kotori.command;

import kotori.taskList.TaskList;
import static kotori.Ui.Ui.printExit;

public class ExitCommand extends Command{
    TaskList taskList;

    public ExitCommand(TaskList list) {
        this.taskList = list;
    }
    public void execute() {
       printExit();
   }
}
