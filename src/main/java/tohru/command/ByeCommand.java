package tohru.command;

import tohru.storage.FileStore;
import tohru.task.TodoList;
import tohru.ui.Ui;

/**
 * Represents the command to exit the chatbot.
 */
public class ByeCommand extends Command {

    /** Prefix used to invoke the bye command. */
    public static final String COMMAND_PREFIX = "bye";

    /**
     * Initialises the command object.
     *
     * @param arguments Arguments passed to the command.
     */
    public ByeCommand(String arguments) {
        super(arguments);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TodoList list, Ui ui, FileStore store) {
        ui.showText("Bye. Hope to see you again soon!");
        ui.closeInput();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
