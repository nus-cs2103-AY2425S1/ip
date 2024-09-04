package genji.command;

import genji.task.TaskList;
import genji.Ui;
import genji.Storage;
public class FindCommand extends Command {
    private String taskDescription;

    public FindCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage s) {
        ui.find(list.findTask(taskDescription));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
