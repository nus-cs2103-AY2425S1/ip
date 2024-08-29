package snipe.command;

import snipe.exception.SnipeException;
import snipe.storage.Storage;
import snipe.core.TaskList;
import snipe.task.Task;
import snipe.util.Ui;

import java.io.IOException;

public class DeleteCommand extends Command{
    private int num;

    public DeleteCommand(int num) {
        this.num = num;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SnipeException, IOException {
        if (this.num > tasks.size() - 1) {
            throw new SnipeException("This list item does not exist!\n"
                    + tasks.listLength());
        } else {
            Task toRemove = tasks.getTask(this.num);
            tasks.deleteTask(this.num);
            storage.saveTaskList(tasks);
            String message = "Noted. I've removed this task:\n"
                    + toRemove.toString()
                    + tasks.listLength();
            ui.printWithLines(message);
        }
    }
}
