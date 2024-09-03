import java.io.IOException;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(String[] inputParts) throws VinegarException {
        if (inputParts.length < 2) throw new VinegarException("Please specify which task to delete.");
        this.index = Integer.parseInt(inputParts[1]) - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws VinegarException, IOException {
        Task taskToRemove = tasks.removeTask(index);
        ui.showDeleted(taskToRemove, tasks.size());
        storage.save(tasks.getTasks());
    }
}
