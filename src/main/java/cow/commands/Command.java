package cow.commands;
import cow.filesaver.FileSaver;
import cow.todoList.TodoList;
import cow.exceptions.CowExceptions;

// solution below inspired by https://github.com/se-edu/addressbook-level2/tree/master

abstract public class Command {
    /**
     * Checks if the command is a command that terminates the program
     * @return boolean
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the task of each command
     * @param todoList the list of the tasks
     * @param fileSaver filesaver object used to write data to txt
     * @throws CowExceptions any exceptions that might arise from the implementation
     */
    public void execute(TodoList todoList, FileSaver fileSaver) throws CowExceptions {};

}
