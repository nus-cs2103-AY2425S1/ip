package cow.commands;

import cow.filesaver.FileSaver;
import cow.todoList.TodoList;
import cow.exceptions.CowExceptions;

// solution below inspired by https://github.com/se-edu/addressbook-level2/tree/master

public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    public static final String COMMAND_EXAMPLE = "unmark 1";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Unmarks an item in the todo as done.\n"
            + "Example: " + COMMAND_EXAMPLE;

    private int index;
    public UnmarkCommand(int index) {
        this.index = index - 1;
    }


    /**
     * Unmarks the task at the specified index in the todo list as incomplete
     * @param todoList the list of the tasks
     * @param fileSaver filesaver object used to write data to txt
     * @throws CowExceptions any exceptions that might arise from the implementation
     */
    @Override
    public void execute(TodoList todoList, FileSaver fileSaver) throws CowExceptions {
        todoList.unmarkTask(this.index);
        fileSaver.saveData(todoList);
    }
}
