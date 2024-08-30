package bimo.command;

import bimo.Storage;
import bimo.tasks.Task;
import bimo.TaskList;
import bimo.Ui;

/**
 * Creates a command that add tasks to list.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Instantiates a command that adds user task.
     *
     * @param task Task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds specified task to user list.
     *
     * @param tasks List of user tasks.
     * @param ui User interface that interacts with users.
     * @param storage Storage that writes and load files.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("    Got it. I've added this task:");
        tasks.addTask(task);
        System.out.println("        " + task.toString());
        String word = tasks.getLength() == 1 ? "task" : "tasks";
        System.out.println(String.format("    Now you have %d %s in the tasks.", tasks.getLength(), word));
        storage.appendToFile(task);
    }
}
