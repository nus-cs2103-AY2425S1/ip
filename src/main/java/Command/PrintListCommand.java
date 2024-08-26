package Command;

import TaskList.TaskList;

import static Ui.Ui.printList;

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
