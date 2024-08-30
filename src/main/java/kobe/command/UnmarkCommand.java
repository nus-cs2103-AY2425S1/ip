package kobe.command;

import kobe.util.Storage;
import kobe.task.Task;
import kobe.task.TaskList;
import kobe.util.Ui;

import java.io.IOException;
public class UnmarkCommand extends Command {
    private final int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex - 1; // Convert to 0-based index
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task task = tasks.getTask(taskIndex);
        task.markAsNotDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
        storage.save(tasks.getTasks());
    }
}