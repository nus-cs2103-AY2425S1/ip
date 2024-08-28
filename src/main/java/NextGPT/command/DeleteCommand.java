package NextGPT.command;
import NextGPT.TaskList;
import NextGPT.Ui;
import NextGPT.Storage;
import NextGPT.NextGPTException;
import java.io.IOException;
import NextGPT.task.Task;

public class DeleteCommand extends Command{
    int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

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
