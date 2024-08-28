package kotori.command;
import kotori.storage.Storage;
import kotori.taskList.TaskList;
import kotori.taskList.IncorrectStateException;

import static kotori.Ui.Ui.printMessage;

/**
 * This class represents a command trying to delete a
 * specific task from the list.
 * */

public class UnmarkCommand extends Command{
    private Storage storage;
    private TaskList list;
    private int index;

    public UnmarkCommand (Storage storage, TaskList list, int index) {
        this.list = list;
        this.index = index;
        this.storage = storage;
    }

    /**
     * Activates the command and try to delete the task.
     * */

    @Override
    public void execute() {
        if (list.size() < index || index <= 0 ) {
            printMessage("Sorry~ can not unmark that task because it does not exist");
        } else {
            try {
                list.unmark(index - 1);
                printMessage(String.format("Alright, Job %s has been marked as not done!\n    %s",
                        index, list.get(index - 1)));
                this.storage.updateFile(list);
            } catch (IncorrectStateException e) {
                printMessage(e.getMessage());
            }
        }
    }
}