
import java.io.IOException;
public class MarkCommand extends Command {
    private int taskIndex;

    public MarkCommand(String arguments) throws JerielException {
        try {
            this.taskIndex = Integer.parseInt(arguments) - 1;
        } catch (NumberFormatException e) {
            throw new JerielException("Invalid task number. Please enter a valid task number.");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JerielException, IOException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new JerielException("Invalid task number. Please enter a valid task number.");
        }
        Task task = tasks.get(taskIndex);
        task.markAsDone();
        ui.showTaskAdded(task, tasks.size());
        storage.save(tasks.getTasks());
    }
}
