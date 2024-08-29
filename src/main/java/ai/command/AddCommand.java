package ai.command;

import ai.task.Task;
import ai.TaskList;
import ai.Ui;

public class AddCommand extends Command {
    private Task task;
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.add(task);
        System.out.println("Task added!!");
        System.out.println(task);
        System.out.println(String.format("You better finish your %d tasks!! ehe :3\n", tasks.size()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}