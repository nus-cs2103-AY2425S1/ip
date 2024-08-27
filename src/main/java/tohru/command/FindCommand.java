package tohru.command;

import tohru.exception.TohruException;
import tohru.storage.FileStore;
import tohru.task.TodoItem;
import tohru.task.TodoList;
import tohru.ui.Ui;

import java.util.ArrayList;

/**
 * Represents the command to search entries for a keyword in the to-do list.
 */
public class FindCommand extends Command {

    /** Prefix used to invoke the find command */
    public static final String COMMAND_PREFIX = "find";

    /**
     * Initialises the command object.
     *
     * @param arguments Arguments passed to the command.
     */
    public FindCommand(String arguments) {
        super(arguments);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TodoList list, Ui ui, FileStore store) throws TohruException {
        // Check if no arguments are provided
        if (super.arguments == null) {
            throw new TohruException("Missing argument: Specify keyword to search");
        }

        ArrayList<TodoItem> filteredItems = list.filter(super.arguments);

        ui.showText(String.format("Here are the %d matching tasks in your list:", filteredItems.size()));
        for (int i = 0; i < filteredItems.size(); i++) {
            ui.showText(String.format("%d. %s", i + 1, filteredItems.get(i)));
        }

    }

}
