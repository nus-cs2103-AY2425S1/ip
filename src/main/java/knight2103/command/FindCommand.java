package knight2103.command;

import knight2103.Ui;
import knight2103.files.Storage;
import knight2103.tasks.TaskList;

/**
 * Models after a command that takes in a key word, finds tasks that matches the key word
 * and shows a list of matched task.
 */
public class FindCommand extends Command {
    FindCommand(CommandVerb verb, String search) {
        super(verb, search); // verb must be CommandVerb.FIND
    }

    /**
     * Executes the FindCommand which finds all tasks with the description that matches
     * with the word to be searched. It returns the list of matched tasks in the bot's GUI.
     *
     * @param tasks The object storing the list of tasks found in the bot.
     * @param ui The user interface of the bot.
     * @param storage The object containing the file that saves the list of tasks.
     * @return The list of matched tasks in the bot's GUI after command execution.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showFind(tasks, this.predicate);
    }
    ;
}
