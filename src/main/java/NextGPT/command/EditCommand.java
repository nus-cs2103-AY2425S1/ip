package nextgpt.command;

import java.io.IOException;

import nextgpt.NextGPTException;
import nextgpt.Storage;
import nextgpt.TaskList;
import nextgpt.Ui;
import nextgpt.task.Task;

/**
 * Subclass of Command that edits tasks from task list
 */
public class EditCommand extends Command {
    protected boolean isMark;
    protected int index;

    /**
     * Constructor of Command that edits tasks from task list
     */
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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws NextGPTException {
        try {
            assert ui != null: "Storage cannot be NULL!";
            assert tasks != null: "Ui cannot be NULL!";
            assert storage != null: "Task list cannot be NULL!";

            Task task = tasks.get(index);
            if (isMark) {
                task.mark();
                storage.add_to_memory(tasks);
                return ui.mark(task);
            } else {
                task.unmark();
                storage.add_to_memory(tasks);
                return ui.mark(task);
            }

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
