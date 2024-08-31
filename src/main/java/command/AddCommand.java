package command;

import task.Task;
import task.TaskList;

public class AddCommand extends Command {
    Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList list) {
        list.add(this.task);
        him.Ui.sayAdded(this.task);
    }
}
