package bob.command;

import bob.tasks.Task;
import bob.tasks.TaskList;
import bob.UI;

public class AddTaskCommand extends Command {

    private final Task task;
    public AddTaskCommand(Task task) {
        super(true);
        this.task = task;
    }

    public void execute(TaskList taskList) {
        taskList.addTask(this.task);
        UI.printAddTask(this.task);
        UI.printCurrentTaskListStatus(taskList);
    }
}
