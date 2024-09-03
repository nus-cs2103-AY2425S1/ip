package kotori.command;
import kotori.storage.Storage;
import kotori.taskList.IncorrectStateException;
import kotori.taskList.TaskList;

import static kotori.ui.Ui.printMessages;

/**
 * This class represents a command trying to delete a
 * specific task from the list.
 * */

public class UnmarkCommand extends Command{
    private Storage storage;
    private TaskList taskList;
    private int index;

    public UnmarkCommand (Storage storage, TaskList taskList, int index) {
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
                taskList.unmark(index - 1);
                this.storage.updateFile(taskList);
                return printMessages(String.format("Alright, Job %s has been marked as not done!\n    %s",
                        index, taskList.get(index - 1)));
            } catch (IncorrectStateException e) {
                return printMessages(e.getMessage());
            }
        }
    }
}