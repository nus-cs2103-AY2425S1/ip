package tohru.command;

import tohru.exception.TohruException;
import tohru.storage.FileStore;
import tohru.task.TodoItem;
import tohru.task.TodoList;
import tohru.ui.Ui;

/**
 * Represents the command to add a to-do task to the to-do list.
 */
public class AddTodoCommand extends Command {

    /** Prefix used to invoke the add to-do command. */
    public static final String COMMAND_PREFIX = "todo";

    /**
     * Initialises the command object.
     *
     * @param arguments Arguments passed to the command.
     */
    public AddTodoCommand(String arguments) {
        super(arguments);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TodoList list, Ui ui, FileStore store) throws TohruException {
        // Check if no arguments are provided
        if (super.arguments == null) {
            throw new TohruException("Missing argument: Please specify description");
        }

        TodoItem newTodo = new TodoItem(super.arguments);
        list.addItem(newTodo);

        ui.showText("Added todo entry:",
                newTodo.toString(),
                String.format("There are now %d total entries", list.getTotal()));

        store.saveTodoList(list.getTodoList());

    }
}
