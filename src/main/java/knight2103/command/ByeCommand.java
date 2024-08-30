package knight2103.command;

import knight2103.tasks.TaskList;
import knight2103.Ui;
import knight2103.files.Storage;

public class ByeCommand extends Command {
    ByeCommand() {
        super(CommandVerb.BYE);
    }

    /**
     * Executes the ByeCommand which shows a goodbye message.
     *
     * @param taskList The class storing the list of tasks found in the bot.
     * @param ui The user interface of the bot.
     * @param storage The class containing the file that saves the list of tasks.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showBye();
    };

    /**
     * Returns whether the bot programme should be exited upon command execution.
     *
     * @return if programme exit after execution.
     */
    public boolean isExit() {
        return true;
    }
}
