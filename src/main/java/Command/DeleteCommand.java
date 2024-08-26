package Command;

import TaskList.Task;
import TaskList.TaskList;

import static Ui.Ui.printMessage;
import static Ui.Ui.printMessages;

public class DeleteCommand extends Command{
    private TaskList list;
    private int index;

    public DeleteCommand (TaskList list, int index) {
        this.list = list;
        this.index = index;
    }

    @Override
    public void execute() {
        if (list.size() < index || index <= 0) {
            printMessage("Sorry~ Can not delete this task as such task does not exist.");
        } else {
            Task task = list.remove(index - 1);
            printMessages(new String[]{"OK~. I've deleted this task:",task.toString(),
                    String.format("Now you have %s tasks in the list",list.size())});
        }
    }

}
