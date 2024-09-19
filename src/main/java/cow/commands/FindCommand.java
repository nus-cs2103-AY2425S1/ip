package cow.commands;

import cow.exceptions.CowExceptions;
import cow.filesaver.FileSaver;
import cow.message.Ui;
import cow.todolist.TodoList;

// solution below inspired by https://github.com/se-edu/addressbook-level2/tree/master

/**
 * Creates a Find command object.
 **/
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public static final String COMMAND_EXAMPLE = "find book";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all task with argument in the description.\n"
            + "Example: " + COMMAND_EXAMPLE;

    private final String description;

    /**
     * Constructs a FindCommand with the specified description.
     *
     * @param description the description to search for in tasks.
     */
    public FindCommand(String description) {
        this.description = description.trim();
    }


    /**
     * Finds all tasks with a matching description in the todo list.
     *
     * @param todoList  the list of tasks.
     * @param ui        the UI object used to interact with the user.
     * @param fileSaver the FileSaver object used to write data to a file.
     * @throws CowExceptions if any exceptions arise during execution.
     */
    @Override
    public void execute(TodoList todoList, Ui ui, FileSaver fileSaver) throws CowExceptions {
        TodoList filtered = todoList.getFilteredByDesc(this.description);
        ui.printFindTask(filtered);
    }
}
