package knight2103.command;

import knight2103.Ui;
import knight2103.files.Storage;
import knight2103.tasks.TaskList;

public class FindCommand extends Command {
    FindCommand(CommandVerb verb, String search) {
        super(verb, search); // verb must be CommandVerb.FIND
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showFind(taskList, this.predicate);
    };

    public boolean isExit() {
        return false;
    }
}
