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
        for (int i = 0; i < tasks.size(); i++) {
            Task temp = tasks.get(i);

            if (task.equals(temp)) {
                return "Oh dear, it seems that you have a dupe :,(( Try adding a different task...\n";
            }
        }
        tasks.add(task);
        return "Task added!!\n" + task + "\n" + String.format("You better finish your %d tasks!! ehe :3\n",
                tasks.size());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
