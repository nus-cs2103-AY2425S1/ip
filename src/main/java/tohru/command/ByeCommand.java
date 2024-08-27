package tohru.command;

import tohru.storage.FileStore;
import tohru.task.TodoList;
import tohru.ui.Ui;

/**
 * ByeCommand represents the command to exit the chatbot
 */
public class ByeCommand extends Command {

    /** Prefix used to invoke the bye command **/
    public static final String COMMAND_PREFIX = "bye";

    public ByeCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute(TodoList list, Ui ui, FileStore store) {
        ui.showText("Bye. Hope to see you again soon!");
        ui.closeInput();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
