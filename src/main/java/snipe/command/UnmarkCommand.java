package snipe.command;

import snipe.core.TaskList;
import snipe.exception.SnipeException;
import snipe.storage.Storage;
import snipe.task.Task;
import snipe.util.Ui;

import java.io.IOException;

public class UnmarkCommand extends Command{
    private int num;

    public UnmarkCommand(int num) {
        this.num = num;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SnipeException, IOException {
        if (this.num > tasks.size() - 1) {
            throw new SnipeException("This list item does not exist!\n"
                    + tasks.listLength());
        } else {
            Task task = tasks.getTask(this.num);
            if (task.getStatus()) {
                task.changeStatus();
                String msg = "OK, I've marked this task as not done yet:\n" +
                        task.toString();
                storage.saveTaskList(tasks);
                ui.printWithLines(msg);
            } else {
                String msg = "This task is currently not done yet!";
                ui.printWithLines(msg);
            }
        }
    }
}
