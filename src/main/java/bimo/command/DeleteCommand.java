package bimo.command;

import bimo.Storage;
import bimo.tasks.Task;
import bimo.TaskList;
import bimo.Ui;

/**
 * Creates a command that delete tasks.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Instantiates command object that deletes task.
     *
     * @param index Index of task in list to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes specified at index in task list.
     *
     * @param tasks List of user tasks.
     * @param ui User interface that interacts with users.
     * @param storage Storage that writes and load files.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (index >= tasks.getLength() || index < 0) {
            ui.showTaskNotFoundError();
            return;
        }
        Task task = tasks.removeTask(index);
        storage.overwriteFile(tasks);
        System.out.println("    Noted. I've removed this task:");
        System.out.println("        " + task.toString());
        String word = tasks.getLength() == 1 ? "task" : "tasks";
        System.out.println(String.format("    Now you have %d %s in the tasks.", tasks.getLength(), word));
    }
}
