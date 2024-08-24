import java.util.ArrayList;

public class DeleteCommand extends Command {
    private final int taskToDelete;

    public DeleteCommand(int taskToDelete) {
        this.taskToDelete = taskToDelete;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) throws LunaException {
        ArrayList<Task> deleted = tasks.deleteTask(taskToDelete);
        storage.saveTasks(deleted);
    }
}
