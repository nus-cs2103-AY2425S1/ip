package command;

import components.Storage;
import components.Ui;
import task.Task;
import task.TaskList;

public class AddCommand extends Command{
    private Task task;

    public AddCommand(Task task) {
        super();
        this.task = task;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        storage.write(TaskList.arrayToNumberedString(tasks));
        ui.showMessage("Got it. I've added this task:\n" +
                task +
                "\nNow you have " + tasks.size() + " tasks in the list.");
    }
}
