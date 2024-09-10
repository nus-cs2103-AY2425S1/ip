package ai.command;

import ai.TaskList;
import ai.Ui;
import ai.task.Task;

/**
 * Executes the add command that adds the task into the TaskList.
 */
public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList tasks, Ui ui) {
        tasks.add(task);

        return "Task added!!\n" + task + "\n" + String.format("You better finish your %d tasks!! ehe :3\n", tasks.size());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
