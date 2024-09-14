package beechat;

import java.io.IOException;

public class DeleteCommand extends Command {
    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex - 1; // Convert to 0-based indexing
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task removedTask = tasks.removeTask(taskIndex);
        System.out.println("OK, I've removed this task:\n" + removedTask);
        storage.saveTasks(tasks.getTasks());
    }
}