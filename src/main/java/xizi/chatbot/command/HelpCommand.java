package xizi.chatbot.command;

import xizi.chatbot.Storage;
import xizi.chatbot.Ui;
import xizi.chatbot.task.TaskList;


/**
 * Represents a command to display the help message.
 * The help message provides guidance on how to use the various commands in the application.
 */
public class HelpCommand implements Command {

    /**
     * Executes the help command, displaying the help message to the user.
     * This method provides an overview of available commands and their usage.
     *
     * @param actions The task list (not used in this command).
     * @param storage The storage handler (not used in this command).
     * @param ui      The user interface for interacting with the user.
     */
    @Override
    public void execute(TaskList actions, Storage storage, Ui ui) {
        ui.printHelp();
    }
}

