package kotori.command;

import kotori.taskList.TaskList;
import static kotori.Ui.Ui.printExit;

/**
 * This class represents a command of exiting the chatbot.
 * */
public class ExitCommand extends Command{
    TaskList list;

    public ExitCommand(TaskList list) {
        this.list = list;
    }

    /**
     * Activates the command and exit the chatbot.
     * */
    public void execute() {
       printExit();
   }
}
