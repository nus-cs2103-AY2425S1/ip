package kotori.command;

import static kotori.ui.Ui.printMessages;

import kotori.storage.Storage;
import kotori.taskList.IncorrectStateException;
import kotori.taskList.TaskList;


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
                taskList.mark(index - 1);
                storage.updateFile(taskList);
                return printMessages(String.format("Nice Job, Job %s has been marked as done!\n    %s",
                        index, taskList.get(index - 1)));
            } catch (IncorrectStateException e) {
                return printMessages(e.getMessage());
            }
        }
    }
}
