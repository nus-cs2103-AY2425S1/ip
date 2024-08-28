package Kotori.Command;

import Kotori.TaskList.TaskList;

import static Kotori.Ui.Ui.printList;

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
