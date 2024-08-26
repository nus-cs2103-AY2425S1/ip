import java.io.IOException;

public class UnmarkCommand extends Command {
    private int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KilluaException, IOException {
        tasks.unmarkTask(taskIndex);
        Task task = tasks.getTasks().get(taskIndex);
        ui.showTaskUnmarked(task);
        storage.save(tasks);
    }
}

