package dumpling.command;

import dumpling.Storage;
import dumpling.Ui;
import dumpling.task.TaskList;

public class FindCommand extends Command {

    private String targetSubstring;

    public FindCommand(String targetSubstring) {
        this.targetSubstring = targetSubstring;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String message = tasks.find(this.targetSubstring);
        ui.echo(message);
    }

    public boolean isExit() {
        return false;
    }
}
