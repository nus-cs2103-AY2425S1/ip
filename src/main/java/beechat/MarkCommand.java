package beechat;

import java.io.IOException;

public class MarkCommand extends Command {
    private final int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex - 1; // convert to 0-based indexing
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task task = tasks.getTask(taskIndex);
        task.mark();
        System.out.println("Nice! I've marked this task as done:\n" + task);
        storage.saveTasks(tasks.getTasks());
    }
}
