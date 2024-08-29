package joe.commands;

import joe.tasks.TaskList;
public class ListCommand extends Command {
    private final TaskList taskList;

    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public void execute() {
        taskList.listTasks();
    }
}