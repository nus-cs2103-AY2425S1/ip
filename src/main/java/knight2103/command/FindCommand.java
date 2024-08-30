package knight2103.command;

import knight2103.tasks.TaskList;
import knight2103.Ui;
import knight2103.files.Storage;

public class FindCommand extends Command {
    FindCommand(CommandVerb verb, String search) {
        super(verb, search); // verb must be CommandVerb.FIND
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showFind(tasks, this.predicate);
    };

    public boolean isExit() {
        return false;
    }
}
