package dudu.command;

import dudu.command.Command;
import dudu.task.Task;
import dudu.utils.Storage;
import dudu.utils.TaskList;
import dudu.utils.UI;

import java.io.IOException;

public class CommandDeadline extends Command {
    Task task;

    public CommandDeadline(Task task) {
        this.task = task;
    }
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws IOException {
        int size = taskList.addTask(task);
        ui.addTask(task, size);
        storage.rewriteFile(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || !(o instanceof CommandDeadline)) {
            return false;
        }
        CommandDeadline other = (CommandDeadline) o;
        return this.task.equals(other.task);
    }
}
