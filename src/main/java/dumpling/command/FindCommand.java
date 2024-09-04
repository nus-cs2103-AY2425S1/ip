package dumpling.command;

import dumpling.Storage;
import dumpling.Ui.Ui;
import dumpling.task.TaskList;

public class FindCommand extends Command {

    private String targetSubstring;

    public FindCommand(String targetSubstring) {
        this.targetSubstring = targetSubstring;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.echo(executeAndReturnLog(taskList, storage));
    }

    @Override
    public String executeAndReturnLog(TaskList taskList, Storage storage) {
        return taskList.find(this.targetSubstring);
    }

    public boolean isExit() {
        return false;
    }
}
