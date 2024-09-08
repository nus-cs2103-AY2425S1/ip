package kotori.command;

import static kotori.ui.Ui.printMessages;

import kotori.storage.Storage;
import kotori.taskList.Task;
import kotori.taskList.TaskList;


/**
 * This class represents a command of trying to
 * delete the task at a certain position of the task list.
 * */

public class DeleteCommand extends Command {
    private Storage storage;
    private TaskList taskList;
    private int index;

    /**
     * Create a delete command
     * */
    public DeleteCommand(Storage storage, TaskList list, int index) {
        this.taskList = list;
        this.index = index;
        this.storage = storage;
    }

    /**
     * Activates the command and try to delete the task.
     * */

    @Override
    public String execute() {
        if (taskList.size() < index || index <= 0) {
            return printMessages("Sorry~ Can not delete this task as such task does not exist.");
        } else {
            Task task = taskList.remove(modify(index));
            storage.updateFile(taskList);
            return printMessages(new String[]{"OK~. I've deleted this task:", task.toString(),
                    String.format("Now you have %s tasks in the list", taskList.size())});
        }
    }
    /**
     * Modify the index for delete operation.
     * @param index the index to be modified
     * @return the modified index
     * */
    private int modify(int index) {
        return index - 1;
    }

}
