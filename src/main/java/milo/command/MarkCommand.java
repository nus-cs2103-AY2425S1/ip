package milo.command;

import milo.lists.ClientsList;
import milo.tasks.Task;
import milo.lists.TaskList;
import milo.ui.ClientUi;
import milo.ui.TaskUi;

public class MarkCommand extends Command {

    private final int taskIndex;
    private Task markedTask;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList, ClientsList clientsList) {
        this.markedTask = taskList.get(this.taskIndex);
        markedTask.mark();
    }

    @Override
    public String commandToString(TaskUi ui, ClientUi cUi, TaskList taskList, ClientsList clientsList) {
        return ui.printMark(this.markedTask);
    }
}
