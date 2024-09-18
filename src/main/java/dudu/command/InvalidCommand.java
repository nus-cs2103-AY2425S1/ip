package dudu.command;

import java.io.IOException;

import dudu.utils.Storage;
import dudu.utils.TaskList;
import dudu.utils.UI;

/**
 * Represents an invalid command.
 */
public class InvalidCommand extends Command {
    private static final String invalidCommandString = "Invalid command. Please type help for the list of commands";

    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) throws IOException {
        return invalidCommandString;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof InvalidCommand;
    }
}
