package dumpling.command;

import dumpling.Storage;
import dumpling.task.TaskList;
import dumpling.ui.Ui;

/**
 * FindCommand class, inherits Command
 */
public class FindCommand extends Command {

    private String targetSubstring;

    /**
     * Constructor for find command class
     *
     * @param targetSubstring Substring to search in task descriptions
     */
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
