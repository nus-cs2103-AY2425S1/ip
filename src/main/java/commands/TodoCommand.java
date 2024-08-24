package commands;

import FileSaver.FileSaver;
import TodoList.TodoList;
import exceptions.CowExceptions;
import exceptions.MissingParametersException;
import message.Message;
import message.Messages;
import tasks.Task;
import tasks.Todo;

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
