package jude.command;

import jude.JudeException;
import jude.Storage;
import jude.TaskList;

/**
 * Represents the command which contains the instructions of series of actions to delete a Task
 * into the TaskList to be executed.
 */
public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes task from the TaskList. Notifies the user that the task has been deleted.
     * @param list Tasklist that stores the task to be deleted.
     * @param storage Savefile hander that will save the removed version of task data to the save file.
     * @throws JudeException If any one of the method fails.
     */
    @Override
    public void execute(TaskList list, Storage storage) throws JudeException {
        list.deleteTask(index);
        setMessage("Task has been deleted.");
        storage.save(list);
    }

    @Override
    public String getType() {
        return "DeleteCommand";
    }

}
