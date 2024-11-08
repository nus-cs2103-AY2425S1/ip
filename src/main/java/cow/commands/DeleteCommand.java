package cow.commands;

import cow.exceptions.CowExceptions;
import cow.filesaver.FileSaver;
import cow.message.Ui;
import cow.tasks.Task;
import cow.todolist.TodoList;

// solution below inspired by https://github.com/se-edu/addressbook-level2/tree/master

/**
 * Creates a delete command object.
 **/
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String COMMAND_EXAMPLE = "delete 1";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Delete an item from the todo list.\n"
            + "Example: " + COMMAND_EXAMPLE;

    private final int index;

    /**
     * Deletes the item from the todo list based on the index entered.
     *
     * @param index the index of the todo list item to delete, starting from 1.
     */
    public DeleteCommand(int index) {
        assert index >= 0 : "index should be >= 0";
        this.index = index - 1;
    }

    /**
     * Deletes a task using the index in the todo list.
     *
     * @param todoList  the list of tasks.
     * @param ui        the UI object used to interact with the user.
     * @param fileSaver the FileSaver object used to write data to a file.
     * @throws CowExceptions if any exceptions arise during the execution.
     */
    @Override
    public void execute(TodoList todoList, Ui ui, FileSaver fileSaver) throws CowExceptions {
        Task t = todoList.delete(this.index);
        fileSaver.saveData(todoList);
        ui.printDeletedTask(t, todoList);
    }
}
