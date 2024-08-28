package kotori.command;

import kotori.storage.Storage;
import kotori.taskList.Task;
import kotori.taskList.TaskList;

import static kotori.Ui.Ui.printMessage;
import static kotori.Ui.Ui.printMessages;

public class DeleteCommand extends Command{
    private Storage storage;
    private TaskList list;
    private int index;

    public DeleteCommand (Storage storage, TaskList list, int index) {
        this.list = list;
        this.index = index;
        this.storage = storage;
    }

    @Override
    public void execute() {
        if (list.size() < index || index <= 0) {
            printMessage("Sorry~ Can not delete this task as such task does not exist.");
        } else {
            Task task = list.remove(index - 1);
            printMessages(new String[]{"OK~. I've deleted this task:",task.toString(),
                    String.format("Now you have %s tasks in the list",list.size())});
            storage.updateFile(list);
        }
    }

}
