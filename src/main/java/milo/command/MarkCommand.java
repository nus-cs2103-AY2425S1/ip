package milo.command;

import milo.tasks.Task;
import milo.tasks.TaskList;
import milo.ui.Ui;

public class MarkCommand extends Command {

    private final int taskIndex;
    private Task markedTask;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList) {
        this.markedTask = taskList.get(this.taskIndex);
        markedTask.mark();
    }

    @Override
    public String commandToString(Ui ui, TaskList taskList) {
        return ui.printMark(this.markedTask);
    }
}
