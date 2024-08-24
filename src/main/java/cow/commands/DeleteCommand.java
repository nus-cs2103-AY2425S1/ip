package cow.commands;

import cow.filesaver.FileSaver;
import cow.todoList.TodoList;
import cow.exceptions.CowExceptions;
import cow.message.Message;
import cow.tasks.Task;

// solution below inspired by https://github.com/se-edu/addressbook-level2/tree/master

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String COMMAND_EXAMPLE = "delete 1";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Delete an item from the todo list.\n"
            + "Example: " + COMMAND_EXAMPLE;

    private final int index;
    public DeleteCommand(int index) {
        this.index = index - 1;
    }

    /**
     * Deletes task using the index in the todo list
     * @param todoList the list of the tasks
     * @param fileSaver filesaver object used to write data to txt
     * @throws CowExceptions any exceptions that might arise from the implementation
     */
    @Override
    public void execute(TodoList todoList, FileSaver fileSaver) throws CowExceptions {
        Task t = todoList.delete(this.index);
        fileSaver.saveData(todoList);
        Message.printDeletedTask(t, todoList);
    }
}
