package nextgpt.command;

import java.io.IOException;

import nextgpt.NextGPTException;
import nextgpt.Storage;
import nextgpt.TaskList;
import nextgpt.Ui;
import nextgpt.task.Task;

/**
 * Subclass of Command that adds tasks to task list.
 */
public class AddCommand extends Command {
    protected Task todo;

    public AddCommand(Task todo){
        this.todo = todo;
    }

    /**
     * Adds task to the given task list.
     * Notifies user of completion before saving new task list.
     *
     * @param tasks Task to be added.
     * @param ui User interface to notify user of completion.
     * @param storage Storage object that saves the new task list.
     * @throws NextGPTException If error occurs while saving new task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws NextGPTException{

        try {
            assert ui != null: "Storage cannot be NULL!";
            assert tasks != null: "Ui cannot be NULL!";
            assert storage != null: "Task list cannot be NULL!";

            tasks.add(todo);
            storage.add_to_memory(tasks);
            return ui.addTask(todo, tasks.size());
        } catch (IOException e) {
            throw new NextGPTException("There was an error saving the file. Please try again.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }


}
