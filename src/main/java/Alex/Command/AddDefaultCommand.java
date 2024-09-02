package Alex.Command;
import Alex.Exceptions.AlexException;
import Alex.Storage.Storage;
import Alex.Task.DefaultTask;
import Alex.Task.TaskList;
import Alex.Ui.Ui;


public class AddDefaultCommand extends CommandBase {
    private final DefaultTask task;

    public AddDefaultCommand(DefaultTask task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AlexException{
        tasks.addTask(task);
        storage.save(tasks.getAllTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
