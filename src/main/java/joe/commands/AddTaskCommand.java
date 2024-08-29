package joe.commands;

import joe.tasks.Task;
import joe.tasks.TaskList;

public class AddTaskCommand extends Command {
    private final TaskList taskList;
    private final Task task;

    public AddTaskCommand(TaskList taskList, Task task) {
        this.taskList = taskList;
        this.task = task;
    }

    @Override
    public void execute() {
        taskList.addTask(task);
    }
}
