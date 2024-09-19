package cow.commands;

import cow.exceptions.CowExceptions;
import cow.filesaver.FileSaver;
import cow.message.Ui;
import cow.todolist.TodoList;

// solution below inspired by https://github.com/se-edu/addressbook-level2/tree/master
/** Creates an UnmarkCommand. **/
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    public static final String COMMAND_EXAMPLE = "unmark 1";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Unmarks an item in the todo as done.\n"
            + "Example: " + COMMAND_EXAMPLE;

    private int index;

    /**
     * Constructor to create an unmark command.
     *
     * @param index of the todo list to unmark.
     */
    public UnmarkCommand(int index) {
        assert index >= 0 : "index should be >= 0";
        this.index = index - 1;
    }


    /**
     * Unmarks the task at the specified index in the todo list as incomplete.
     *
     * @param todoList the list of the tasks.
     * @param fileSaver filesaver object used to write data to txt.
     * @throws CowExceptions any exceptions that might arise from the implementation.
     */
    @Override
    public void execute(TodoList todoList, Ui ui, FileSaver fileSaver) throws CowExceptions {
        todoList.unmarkTask(this.index, ui);
        fileSaver.saveData(todoList);
    }
}
