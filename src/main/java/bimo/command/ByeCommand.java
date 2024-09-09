package bimo.command;

import bimo.Storage;
import bimo.TaskList;
import bimo.Ui;
/**
 * Creates a command that exits from bot.
 */
public class ByeCommand extends Command {

    /**
     * Quits chatbot.
     *
     * @param tasks List of user tasks.
     * @param ui User interface that interacts with users.
     * @param storage Storage that writes and load files.
     * @return Response of chatbot.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "Bye!!! Thanks for chatting!";
    }
}
