package commands;

import FileSaver.FileSaver;
import TodoList.TodoList;
import exceptions.CowExceptions;
import message.Message;
import tasks.Task;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String COMMAND_EXAMPLE = "delete 1";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Delete an item from the todo list.\n"
            + "Example: " + COMMAND_EXAMPLE;

    private final int index;
    public DeleteCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public void execute(TodoList todoList, FileSaver fileSaver) throws CowExceptions {
        Task t = todoList.delete(this.index);
        fileSaver.saveData(todoList);
        Message.printDeletedTask(t, todoList);
    }
}
