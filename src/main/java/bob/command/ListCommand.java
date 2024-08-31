package bob.command;

import bob.tasks.TaskList;

import bob.UI;

public class ListCommand extends Command{

    public ListCommand() {
        super(true);
    }

    public void execute(TaskList taskList) {
        UI.printTaskList(taskList);
    }
}
