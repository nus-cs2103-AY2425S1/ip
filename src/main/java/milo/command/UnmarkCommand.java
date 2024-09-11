package milo.command;

import milo.tasks.Task;
import milo.tasks.TaskList;
import milo.ui.Ui;

public class UnmarkCommand extends Command {

    private final int taskIndex;
    private Task unmarkedTask;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList) {
        this.unmarkedTask = taskList.get(this.taskIndex);
        unmarkedTask.unmark();
    }

    @Override
    public String commandToString(Ui ui, TaskList taskList) {
        return ui.printUnmark(this.unmarkedTask);
    }
}
