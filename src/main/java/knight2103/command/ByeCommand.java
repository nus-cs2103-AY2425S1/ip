package knight2103.command;

import knight2103.Ui;
import knight2103.files.Storage;
import knight2103.tasks.TaskList;

/**
 * Models after a command that indicates an exit of the application or the bot stops operating.
 */
public class ByeCommand extends Command {
    ByeCommand() {
        super(CommandVerb.BYE);
    }

    /**
     * Executes the ByeCommand which shows a goodbye message.
     *
     * @param tasks The object storing the list of tasks found in the bot.
     * @param ui The user interface of the bot.
     * @param storage The object containing the file that saves the list of tasks.
     * @return The message to be shown by the bot in GUI after command execution.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showBye();
    }

    ;
}
