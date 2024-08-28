package Kotori.Command;

import Kotori.TaskList.TaskList;
import static Kotori.Ui.Ui.printExit;
public class ExitCommand extends Command{
    TaskList list;

    public ExitCommand(TaskList list) {
        this.list = list;
    }
    public void execute() {
       printExit();
   }
}
