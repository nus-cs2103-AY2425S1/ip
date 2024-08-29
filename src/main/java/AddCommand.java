import java.io.IOException;

public class AddCommand extends Command{
    private Task task;

    public AddCommand(Task task) {
        super();
        this.task = task;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NayanaException {
        tasks.addTask(task);
        storage.writeToFile(tasks.getTasks());
        ui.printAddTask(task, tasks.getSize());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
