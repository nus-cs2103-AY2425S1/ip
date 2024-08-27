package tohru.command;

import tohru.exception.TohruException;
import tohru.storage.FileStore;
import tohru.task.TodoList;
import tohru.ui.Ui;

/**
 * DeleteCommand represents the command to delete an entry in the to-do list
 */
public class DeleteCommand extends Command {

    /** Prefix used to invoke the delete command **/
    public static final String COMMAND_PREFIX = "delete";

    public DeleteCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute(TodoList list, Ui ui, FileStore store) throws TohruException {
        // Check if no arguments are provided
        if (super.arguments == null) {
            throw new TohruException("Missing argument: Specify index to delete");
        }

        int itemIndex = -1;

        // Check for valid index
        try {
            itemIndex = Integer.parseInt(arguments) - 1;
        } catch (NumberFormatException e) {
            throw new TohruException(String.format("%s is not valid index", arguments));
        }

        String storedItemStatus = list.getItemStatus(itemIndex);

        list.deleteItem(itemIndex);

        ui.showText("Alright! I have removed this task from list:");
        ui.showText(storedItemStatus);

        store.saveTodoList(list.getTodoList());

    }
}
