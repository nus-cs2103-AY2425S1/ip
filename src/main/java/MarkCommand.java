import java.io.IOException;

public class MarkCommand extends Command {
    private int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task task = tasks.get(taskIndex);
        task.markAsDone();
        storage.save(tasks.toArrayList());
        ui.showMarkedTask(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
