package lict.command;

import lict.Storage;
import lict.TaskList;
import lict.Ui;
import lict.LictException;
import lict.task.Task;

public class MarkCommand extends Command {
    private String taskNum;

    public MarkCommand(String taskNum) {
        this.taskNum = taskNum;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws LictException {
        try {
            int index = Integer.parseInt(taskNum) - 1;
            if (index < 0) {
                throw new LictException("Invalid task number. lict.task.Task numbers should all be positive.");
            } else if (index >= tasks.size()) {
                throw new LictException("Invalid task number. There are only " + tasks.size() + " tasks in the list.");
            } else {
                Task t = tasks.get(index);
                t.isMarked(true);
                ui.hasMarkedTask(t);
                storage.save(tasks);
            }
        } catch (NumberFormatException e) {
            throw new LictException("Please enter a valid integer index. For eg. 'mark 1'");
        }
    }
}
