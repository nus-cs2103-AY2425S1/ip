package kotori.command;

import static kotori.ui.Ui.printMessages;

import kotori.storage.Storage;
import kotori.tasklist.IncorrectStateException;
import kotori.tasklist.TaskList;


/**
 * This class represents a command trying to delete a
 * specific task from the list.
 * */

public class UnmarkCommand extends Command {
    private Storage storage;
    private TaskList taskList;
    private int index;
    /**
     * Create a unmark command.
     * */
    public UnmarkCommand(Storage storage, TaskList taskList, int index) {
        this.taskList = taskList;
        this.index = index;
        this.storage = storage;
    }

    /**
     * Activates the command and try to delete the task.
     * */

    @Override
    public String execute() {
        if (taskList.size() < index || index <= 0 ) {
            return printMessages("Sorry~ can not unmark that task because it does not exist");
        } else {
            try {
                taskList.unmark(modify(index));
                this.storage.updateFile(taskList);
                return printMessages(String.format("Alright, Job %s has been marked as not done!\n    %s",
                        index, taskList.get(modify(index))));
            } catch (IncorrectStateException e) {
                return printMessages(e.getMessage());
            }
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
