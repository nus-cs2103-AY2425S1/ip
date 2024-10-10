package kotori.command;

import static kotori.ui.Ui.printMessages;

import kotori.storage.Storage;
import kotori.tasklist.IncorrectStateException;
import kotori.tasklist.TaskList;


/**
 * This represents a command of trying to mark a task
 * as complete.
 * */

public class MarkCommand extends Command {
    private Storage storage;
    private TaskList taskList;
    private int index;

    /**
     * Create a mark command.
     * */
    public MarkCommand(Storage storage, TaskList taskList, int index) {
        this.taskList = taskList;
        this.index = index;
        this.storage = storage;
    }

    /***
     * Activate the command and try to mark the task.
     */

    @Override
    public String execute() {
        if (taskList.size() < index || index <= 0) {
            return printMessages("Sorry~ can not mark that task because it does not exist");
        } else {
            try {
                taskList.mark(modify(index));
                storage.updateFile(taskList);
                return printMessages(String.format("Nice Job, Job %s has been marked as done!\n    %s",
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
