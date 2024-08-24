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

    public String getDescription() {
        return this.description;
    }

    /**
     * Creates a todo task and adds to the todo list
     * @param todoList the list of the tasks
     * @param fileSaver filesaver object used to write data to txt
     * @throws CowExceptions any exceptions that might arise from the implementation
     */
    @Override
    public void execute(TodoList todoList, FileSaver fileSaver) throws CowExceptions {
        Task t = new Todo(this.description);
        todoList.add(t);
        Message.printAddedTask(t, todoList);
        fileSaver.saveData(todoList);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TodoCommand that = (TodoCommand) obj;
        return description.equals(that.description);
    }
}
