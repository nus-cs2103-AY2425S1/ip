import java.io.IOException;

public class CommandTodo extends Command {

    Task task;

    public CommandTodo(Task task) {
        this.task = task;
    }
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws IOException {
        int size = taskList.addTask(task);
        ui.addTask(task, size);
        storage.rewriteFile(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
