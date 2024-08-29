package command;
import fridayException.FridayException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;
import java.io.IOException;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws FridayException {
        tasks.addTask(task);
        ui.showTaskAdded(task, tasks.size());
        try {
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            throw new FridayException("Error saving tasks to file.");
        }
    }
}
