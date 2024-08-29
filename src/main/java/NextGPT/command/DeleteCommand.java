package NextGPT.command;
import NextGPT.TaskList;
import NextGPT.Ui;
import NextGPT.Storage;
import NextGPT.NextGPTException;
import java.io.IOException;
import NextGPT.task.Task;

/**
 * Subclass of Command that deletes tasks from task list
 */
public class DeleteCommand extends Command{
    int index;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NextGPTException {
        try {
            Task deletedTask = tasks.remove(index);
            ui.delete(deletedTask, tasks.size());
            storage.add_to_memory(tasks);
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
