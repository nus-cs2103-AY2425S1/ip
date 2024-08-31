package commands;

import task.TaskList;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList) {
        taskList.displayList();
    }
}
