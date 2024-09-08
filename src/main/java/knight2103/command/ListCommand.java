package knight2103.command;

import knight2103.Ui;
import knight2103.files.Storage;
import knight2103.tasks.TaskList;

public class ListCommand extends Command {
    ListCommand() {
        super(CommandVerb.LIST);
    }

    /**
     * Executes the ListCommand which list out all the tasks stored in
     * the list of tasks in a certain format.
     *
     * @param tasks The class storing the list of tasks found in the bot.
     * @param ui The user interface of the bot.
     * @param storage The class containing the file that saves the list of tasks.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showList(tasks);
    };
}
