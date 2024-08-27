import java.lang.annotation.Inherited;

public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        ui.printTaskAdded(task);
        storage.saveTasks(tasks.getTasks());
    }
}
