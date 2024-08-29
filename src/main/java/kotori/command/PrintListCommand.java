package kotori.command;

import kotori.taskList.TaskList;

import static kotori.Ui.Ui.printList;

public class PrintListCommand extends Command {
    private TaskList taskList;

    public PrintListCommand(TaskList list) {
        this.taskList = list;
    }
    @Override
    public void execute() {
        printList(taskList);
    }
}
