package commands;
import FileSaver.FileSaver;
import TodoList.TodoList;
import exceptions.CowExceptions;

abstract public class Command {
    public boolean isExit() {
        return false;
    }

    public void execute(TodoList todoList, FileSaver fileSaver) throws CowExceptions {};

}
