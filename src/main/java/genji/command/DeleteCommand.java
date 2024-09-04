package genji.command;

import genji.GenjiException;
import genji.task.TaskList;
import genji.task.Task;
import genji.Ui;
import genji.Storage;

/**
 * A class that deals with deleting command
 */
public class DeleteCommand extends Command{
    private int index;

    /**
     * Constructor of deleting command
     * @param index Number concerning which task to be deleted
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Calling the delete method in task list class
     * @param list Task list to be modified
     * @param ui Ui for display
     * @param s Storage for saving
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage s) {
        if (list.size() == 0) {
            System.out.println("No task on list, cannot delete");
        } else {
            try {
                Task temp = list.get(index);
                list.delete(index);
                ui.delete(temp, list);
                s.saveList(list);
            } catch (IndexOutOfBoundsException i) {
                ui.showError(new GenjiException(
                        "Please input a integer smaller than the number of tasks").getMessage());
            }
        }
    }

    /**
     * Distinguishes if it is a bye command
     * @return Exit
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
