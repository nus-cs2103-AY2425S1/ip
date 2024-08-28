package kotori.command;

import kotori.taskList.TaskList;

import static kotori.Ui.Ui.printList;

public class PrintListCommand extends Command {
    private TaskList list;

    public PrintListCommand(TaskList list) {
        this.list = list;
    }
    @Override
    public void execute() {
        printList(list);
    }
}
