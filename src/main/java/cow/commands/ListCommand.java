package cow.commands;

import cow.exceptions.CowExceptions;
import cow.filesaver.FileSaver;
import cow.message.Message;
import cow.todoList.TodoList;

// solution below inspired by https://github.com/se-edu/addressbook-level2/tree/master

public class ListCommand extends Command{
    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all tasks in todo list.\n"
            + "Example: " + COMMAND_WORD;

    /**
     * List all the task in the todo list
     * @param todoList the list of the tasks
     * @param fileSaver filesaver object used to write data to txt
     * @throws CowExceptions any exceptions that might arise from the implementation
     */
    @Override
    public void execute(TodoList todoList, FileSaver fileSaver) {
        Message.printList(todoList);
    }
}
