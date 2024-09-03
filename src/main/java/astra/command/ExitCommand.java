package astra.command;

import astra.Storage;
import astra.TaskList;
import astra.Ui;

/**
 * Represents a command to exit the chatbot.
 */
public class ExitCommand extends Command {
    public ExitCommand() {

    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
