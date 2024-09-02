package chatBot.command;

import chatBot.bot.Storage;
import chatBot.bot.TaskList;
import chatBot.bot.Ui;

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

    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    public boolean isExit() {
        return false;
    }
}
