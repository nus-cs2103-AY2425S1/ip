package cow.commands;

import cow.filesaver.FileSaver;
import cow.todoList.TodoList;
import cow.exceptions.CowExceptions;
import cow.message.Message;
import cow.tasks.Task;
import cow.tasks.Todo;

// solution below inspired by https://github.com/se-edu/addressbook-level2/tree/master

public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    public static final String COMMAND_EXAMPLE = "todo buy groceries";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a Todo item to the list.\n"
            + "Example: " + COMMAND_EXAMPLE;

    private final String description;

    public TodoCommand(String description) {

        this.description = description.trim();
    }

    @Override
    public void execute(TodoList todoList, FileSaver fileSaver) throws CowExceptions {
        Task t = new Todo(this.description);
        todoList.add(t);
        Message.printAddedTask(t, todoList);
        fileSaver.saveData(todoList);
    }
}
