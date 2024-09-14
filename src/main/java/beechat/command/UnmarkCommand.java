package beechat.command;

import beechat.util.Storage;
import beechat.task.Task;
import beechat.task.TaskList;
import beechat.util.Ui;

import java.io.IOException;

public class UnmarkCommand extends Command {
    private final int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex - 1; //convert to 0-based indexing
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task task = tasks.getTask(taskIndex);
        task.unmark();
        System.out.println("OK, I've marked this task as not done yet:\n" + task);
        storage.saveTasks(tasks.getTasks());
    }
}
