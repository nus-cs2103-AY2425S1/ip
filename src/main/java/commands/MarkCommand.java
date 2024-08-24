package commands;

import FileSaver.FileSaver;
import TodoList.TodoList;
import exceptions.CowExceptions;
import message.Message;
import tasks.Task;
import tasks.Todo;

public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    public static final String COMMAND_EXAMPLE = "mark 1";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks an item in the todo as done.\n"
            + "Example: " + COMMAND_EXAMPLE;

    private int index;
    public MarkCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public void execute(TodoList todoList, FileSaver fileSaver) throws CowExceptions {
        todoList.markTask(this.index);
        fileSaver.saveData(todoList);
    }
}
