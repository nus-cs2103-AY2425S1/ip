package NextGPT.command;
import java.io.IOException;
import NextGPT.TaskList;
import NextGPT.Ui;
import NextGPT.Storage;
import NextGPT.NextGPTException;
import NextGPT.task.Task;

/**
 * Subclass of Command that edits tasks from task list
 */
public class EditCommand extends Command{
    protected boolean isMark;
    protected int index;

    public EditCommand(Boolean isMark, int index) {
        this.isMark = isMark;
        this.index = index;
    }

    /**
     * Edits task in the given task list.
     * Notifies user of completion before saving new task list.
     *
     * @param tasks Task to be edited.
     * @param ui User interface to notify user of completion.
     * @param storage Storage object that saves the new task list.
     * @throws NextGPTException If error occurs while saving new task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NextGPTException{
        try {
            Task task = tasks.get(index);
            if (isMark) {
                task.mark();
                ui.mark(task);
            } else {
                task.unmark();
                ui.mark(task);
            }
            storage.add_to_memory(tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new NextGPTException("Task list index is out of bounds!");
        } catch (IOException e) {
            throw new NextGPTException("There was an error saving the file. Please try again.");
        }

    }
    @Override
    public boolean isExit() {
        return false;
    }
}
