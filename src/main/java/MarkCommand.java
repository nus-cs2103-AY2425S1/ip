import java.io.IOException;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(String input) throws TaskManagerException {
        try {
            this.index = Integer.parseInt(input.substring(5)) - 1;
        } catch (NumberFormatException e) {
            throw new TaskManagerException("Error: Invalid task number.");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, TaskManagerException {
        if (index < 0 || index >= tasks.size()) {
            throw new TaskManagerException("Error: Invalid task number.");
        }
        Task task = tasks.get(index);
        task.markAsDone();
        ui.showTaskMarked(task);
        storage.save(tasks.getTasks());
    }
}
