import java.io.IOException;

public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KilluaException, IOException {
        tasks.deleteTask(taskIndex);
        Task task = tasks.getTasks().get(taskIndex);
        ui.showTaskDeleted(task);
        storage.save(tasks);
    }
}
