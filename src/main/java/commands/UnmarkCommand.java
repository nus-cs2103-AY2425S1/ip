package commands;

import FileSaver.FileSaver;
import TodoList.TodoList;
import exceptions.CowExceptions;

public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    public static final String COMMAND_EXAMPLE = "unmark 1";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Unmarks an item in the todo as done.\n"
            + "Example: " + COMMAND_EXAMPLE;

    private int index;
    public UnmarkCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public void execute(TodoList todoList, FileSaver fileSaver) throws CowExceptions {
        todoList.unmarkTask(this.index);
        fileSaver.saveData(todoList);
    }
}
