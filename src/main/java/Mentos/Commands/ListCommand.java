package Mentos.Commands;

import Mentos.task.TaskList;

public class ListCommand extends Command {

    public ListCommand() {
        super("list");
    }

    @Override
    public String execute(TaskList taskList) {
        return super.getGui().displayTasks(taskList);
    }
}
