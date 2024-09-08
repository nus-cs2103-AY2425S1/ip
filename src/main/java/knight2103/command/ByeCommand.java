package knight2103.command;

import knight2103.Ui;
import knight2103.files.Storage;
import knight2103.tasks.TaskList;

public class ByeCommand extends Command {
    ByeCommand() {
        super(CommandVerb.BYE);
    }

    /**
     * Executes the ByeCommand which shows a goodbye message.
     *
     * @param tasks The class storing the list of tasks found in the bot.
     * @param ui The user interface of the bot.
     * @param storage The class containing the file that saves the list of tasks.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showBye();
    };
}
