package cow.commands;

import cow.exceptions.CowExceptions;
import cow.filesaver.FileSaver;
import cow.message.Ui;
import cow.todolist.TodoList;

// solution below inspired by https://github.com/se-edu/addressbook-level2/tree/master
/** An abstract class for all commands. **/
public abstract class Command {
    public static final Object MESSAGE_USAGE = "";

    /**
     * Checks if the command is a command that terminates the program.
     *
     * @return boolean.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the task of each command.
     *
     * @param todoList the list of the tasks.
     * @param fileSaver filesaver object used to write data to txt.
     * @throws CowExceptions any exceptions that might arise from the implementation.
     */
    public void execute(TodoList todoList, Ui ui, FileSaver fileSaver) throws CowExceptions {};

}
