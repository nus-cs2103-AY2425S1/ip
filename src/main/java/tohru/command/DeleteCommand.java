package tohru.command;

import tohru.exception.TohruException;
import tohru.storage.FileStore;
import tohru.task.TodoList;
import tohru.ui.Ui;

/**
 * Represents the command to delete an entry in the to-do list.
 */
public class DeleteCommand extends Command {

    /** Prefix used to invoke the delete command. */
    public static final String COMMAND_PREFIX = "delete";

    /**
     * Initialises the command object.
     *
     * @param arguments Arguments passed to the command.
     */
    public DeleteCommand(String arguments) {
        super(arguments);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TodoList list, Ui ui, FileStore store) throws TohruException {
        // Check if no arguments are provided
        if (super.arguments == null) {
            throw new TohruException("Missing argument: Specify index to delete");
        }

        int itemIndex = Parser.parseIntArgument(super.arguments);

        String storedItemStatus = list.getItemStatus(itemIndex);

        list.deleteItem(itemIndex);

        ui.showText("Alright! I have removed this task from list:", storedItemStatus);

        store.saveTodoList(list.getTodoList());

    }
}
