// DeleteCommand.java
import java.io.IOException;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index - 1; // Adjusting for zero-based indexing
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        if (index < 0 || index >= taskList.size()) {
            ui.showTaskNumInvalid();
        }
        Task removedTask = taskList.deleteTask(index);
        ui.showTaskDeleted(removedTask, taskList);
        storage.saveTasksToFile(taskList.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

