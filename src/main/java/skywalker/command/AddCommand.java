package skywalker.command;
import skywalker.storage.Storage;
import skywalker.task.Task;
import skywalker.task.TaskList;
import skywalker.ui.Ui;

import java.io.IOException;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the add command
     * displays the task saved and saves the updated task.
     *
     * @param tasks The task list which would be added to
     * @param ui The UI object is used to show message to user
     * @param storage The place where task list are stored
     * @throws IOException IOException happens if an I/O error occurs
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.addTask(task);
        ui.showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        ui.showLine();
        storage.save(tasks);
    }
}
