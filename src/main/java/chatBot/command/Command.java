package chatbot.command;

import chatbot.bot.Storage;
import chatbot.bot.TaskList;
import chatbot.bot.Ui;

/**
 * Command is the parent class for polymorphism to be applied.
 *
 * The main method execute, executes the command and generates a response in
 * any of the combination of the following: tasklist, ui and storage
 *
 * isExit method returns False unless it is an exitcommand
 */
public abstract class Command {
    Command() {}

    /** Execute the command */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage);

    /** Identify whether to stop the loop */
    public boolean isExit() {
        return false;
    }
}
