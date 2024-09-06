package kotori.command;

import static kotori.ui.Ui.printList;

import kotori.taskList.TaskList;

/**
 * This class represents the command of printing the list.
 * */

public class PrintListCommand extends Command {
    private TaskList taskList;

    public PrintListCommand(TaskList list) {
        this.taskList = list;
    }

    /**
     * Activates the command and print the list.
     * */

    @Override
    public String execute() {
        return printList(taskList);
    }
}
