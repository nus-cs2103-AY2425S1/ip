package xizi.chatbot.command;

import xizi.chatbot.Storage;
import xizi.chatbot.Ui;
import xizi.chatbot.task.TaskList;

/**
 * The {@code ByeCommand} class represents a command to terminate the application.
 * This command displays a goodbye message to the user.
 */
public class ByeCommand implements Command {

    /**
     * Executes the bye command, which displays a goodbye message to the user.
     * This method is called when the user wants to exit the application.
     *
     * @param actions the {@code TaskList} containing the user's tasks
     * @param storage the {@code Storage} used to save and load tasks
     * @param ui      the {@code Ui} used to interact with the user
     */
    @Override
    public void execute(TaskList actions, Storage storage, Ui ui) {
        ui.showGoodbyeMessage();
    }
}
