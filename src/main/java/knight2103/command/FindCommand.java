package knight2103.command;

import knight2103.Ui;
import knight2103.files.Storage;
import knight2103.tasks.TaskList;

public class FindCommand extends Command {
    FindCommand(CommandVerb verb, String search) {
        super(verb, search); // verb must be CommandVerb.FIND
    }

    /**
     * Executes the FindCommand which finds all the tasks that contains the description that
     * matches with the word to be searched and prints out the list of matched tasks.
     *
     * @param tasks The class storing the list of tasks found in the bot.
     * @param ui The user interface of the bot.
     * @param storage The class containing the file that saves the list of tasks.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showFind(tasks, this.predicate);
    };
}
