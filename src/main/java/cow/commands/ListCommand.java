package cow.commands;

import cow.filesaver.FileSaver;
import cow.message.Ui;
import cow.todolist.TodoList;

// solution below inspired by https://github.com/se-edu/addressbook-level2/tree/master

/** Creates a List command object. **/

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all tasks in todo list.\n"
            + "Example: " + COMMAND_WORD;

    /**
     * Lists all the task in the todo list.
     *
     * @param todoList the list of the tasks.
     * @param fileSaver filesaver object used to write data to txt.
     */
    @Override
    public void execute(TodoList todoList, Ui ui, FileSaver fileSaver) {
        ui.printList(todoList);
    }
}
