package kotori.command;

import static kotori.ui.Ui.printListWithMessages;

import kotori.tasklist.TaskList;

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
        return printListWithMessages(taskList, String.format("Now you have %d tasks in list",
                taskList.size()));
    }
}
