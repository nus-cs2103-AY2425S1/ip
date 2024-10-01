package garfield.commands;

import garfield.storage.Storage;
import garfield.tasks.TaskList;
import garfield.ui.Ui;

/**
 * The ExitCommand class represents a command to exit the Garfield chatbot application.
 * When executed, it signals that the application should terminate.
 */
public class ExitCommand extends Command {

    /**
     * Constructs a new ExitCommand instance.
     */
    public ExitCommand() {}

    /**
     * Indicates that this command should cause the application to exit.
     *
     * @return {@code true}, indicating that the application should terminate after this command.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "Finally. Try not to come back too soon.";
    }
}
