package knight2103.command;

import knight2103.tasks.TaskList;
import knight2103.Ui;
import knight2103.files.Storage;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks);
    };

    /**
     * Returns whether the bot programme should be exited upon command execution.
     *
     * @return if programme exit after execution.
     */
    public boolean isExit() {
        return false;
    }
}
