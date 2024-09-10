package tohru.command;

import tohru.storage.FileStore;
import tohru.task.TodoList;
import tohru.ui.Ui;

/**
 * Represents the command to list the entries in the to-do list.
 */
public class ListCommand extends Command {

    /** Prefix used to invoke the list command. */
    public static final String COMMAND_PREFIX = "list";

    /**
     * Initialises the command object.
     *
     * @param arguments Arguments passed to the command.
     */
    public ListCommand(String arguments) {
        super(arguments);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TodoList list, Ui ui, FileStore store) {
        String[] itemsListString = TodoList.getItemisedItemsString(list.getTodoList());
        ui.showText(String.format("These are %s entries on your todo:", list.getTotal()),
                String.join(System.lineSeparator(), itemsListString));
    }

}
