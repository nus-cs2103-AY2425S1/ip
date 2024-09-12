package nextgpt.command;


import java.io.IOException;

import nextgpt.NextGPTException;
import nextgpt.task.Task;
import nextgpt.TaskList;
import nextgpt.Ui;
import nextgpt.Storage;


/**
 * Subclass of Command that deletes tasks from task list
 */

public class DeleteCommand extends Command{
    private int index;
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Remove task to the given task list.
     * Notifies user of completion before saving new task list.
     *
     * @param tasks Task to be removed.
     * @param ui User interface to notify user of completion.
     * @param storage Storage object that saves the updated task list.
     * @throws NextGPTException If error occurs while saving new task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws NextGPTException {
        try {
            assert ui != null: "Storage cannot be NULL!";
            assert tasks != null: "Ui cannot be NULL!";
            assert storage != null: "Task list cannot be NULL!";

            Task deletedTask = tasks.remove(index);
            storage.add_to_memory(tasks);
            return ui.delete(deletedTask, tasks.size());
        } catch (IOException e) {
            throw new NextGPTException("There was an error saving the file. Please try again.");
        } catch (IndexOutOfBoundsException e) {
            throw new NextGPTException("Task list index is out of bounds!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
