package cow.commands;

import cow.filesaver.FileSaver;
import cow.message.Message;
import cow.todoList.TodoList;

// solution below inspired by https://github.com/se-edu/addressbook-level2/tree/master

public class ListCommand extends Command{
    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all tasks in todo list.\n"
            + "Example: " + COMMAND_WORD;
    @Override
    public void execute(TodoList todoList, FileSaver fileSaver) {
        Message.print(todoList.toString());
    }
}
