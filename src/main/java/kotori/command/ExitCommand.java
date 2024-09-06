package kotori.command;

import static kotori.ui.Ui.printExit;

import kotori.taskList.TaskList;


/**
 * This class represents a command of exiting the chatbot.
 * */

public class ExitCommand extends Command {
    private TaskList taskList;

    public ExitCommand(TaskList list) {
        this.taskList = list;
    }

    /**
     * Activates the command and exit the chatbot.
     * */
    public String execute() {
        return printExit();
    }
}
