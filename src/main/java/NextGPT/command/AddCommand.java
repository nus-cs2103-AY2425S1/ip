package NextGPT.command;
import java.io.IOException;
import NextGPT.TaskList;
import NextGPT.Ui;
import NextGPT.Storage;
import NextGPT.NextGPTException;
import NextGPT.task.Task;

/**
 * Subclass of Command that adds tasks to task list.
 */
public class AddCommand extends Command{
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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NextGPTException{
        tasks.add(todo);
        ui.addTask(todo, tasks.size());
        try {
            storage.add_to_memory(tasks);
        } catch(IOException e) {
            throw new NextGPTException("There was an error saving the file. Please try again.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }


}
