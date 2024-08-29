package kotori.command;

import kotori.taskList.TaskList;
import static kotori.ui.Ui.printExit;

/**
 * This class represents a command of exiting the chatbot.
 * */

public class ExitCommand extends Command{
    TaskList taskList;

    public ExitCommand(TaskList list) {
        this.taskList = list;
    }

    /**
     * Activates the command and exit the chatbot.
     * */
    public void execute() {
       printExit();
   }
}
