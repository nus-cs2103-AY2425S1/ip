package tohru.command;

import tohru.exception.TohruException;
import tohru.storage.FileStore;
import tohru.task.TodoList;
import tohru.ui.Ui;

/**
 * Represents the command to unmark a completed task as incomplete in the to-do list.
 */
public class UnmarkCommand extends Command {

    /** Prefix used to invoke the mark command. */
    public static final String COMMAND_PREFIX = "unmark";

    /**
     * Initialises the command object.
     *
     * @param arguments Arguments passed to the command.
     */
    public UnmarkCommand(String arguments) {
        super(arguments);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TodoList list, Ui ui, FileStore store) throws TohruException {
        // Check if no arguments are provided
        if (super.arguments == null) {
            throw new TohruException("Missing argument: Specify index to unmark");
        }

        int itemIndex = Parser.parseIntArgument(super.arguments);

        list.markIncomplete(itemIndex);

        ui.showText("Alright! I have set this task as not done:",
                list.getItemStatus(itemIndex));

        store.saveTodoList(list.getTodoList());

    }

}
