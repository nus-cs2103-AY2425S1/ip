import java.io.IOException;

public class DeleteCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // Task
        int index = ui.getDeleteIndex();

        // Ui response + deletion
        Task task = tasks.getTask(index - 1);
        ui.deleteResponse(task, tasks, tasks.size());
        tasks.deleteTask(index - 1);

        // Storage
        try {
            storage.write(tasks);
        } catch (IOException io) {
            System.out.println("Error in writing history" + io.getMessage());
        }
    }

    @Override
    public Boolean isExit() {
        return false;
    }
}
