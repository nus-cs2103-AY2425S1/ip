package cow.commands;
import cow.filesaver.FileSaver;
import cow.todoList.TodoList;
import cow.exceptions.CowExceptions;

// solution below inspired by https://github.com/se-edu/addressbook-level2/tree/master

abstract public class Command {
    public boolean isExit() {
        return false;
    }

    public void execute(TodoList todoList, FileSaver fileSaver) throws CowExceptions {};

}
