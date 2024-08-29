import java.io.IOException;

public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(String arguments) throws JerielException {
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
        tasks.deleteTask(taskIndex);
        ui.showTaskDeleted(task, tasks.size());
        storage.save(tasks.getTasks());
    }
}
