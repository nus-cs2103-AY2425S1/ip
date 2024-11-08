package cow.commands;

import cow.exceptions.CowExceptions;
import cow.filesaver.FileSaver;
import cow.message.Ui;
import cow.tasks.Task;
import cow.tasks.Todo;
import cow.todolist.TodoList;


// solution below inspired by https://github.com/se-edu/addressbook-level2/tree/master

/**
 * Creates a TodoCommand.
 **/
public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    public static final String COMMAND_EXAMPLE = "todo buy groceries";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a Todo item to the list.\n"
            + "Example: " + COMMAND_EXAMPLE;

    private final String description;

    /**
     * Creates a TodoCommand instance.
     *
     * @param description the description of the todo task.
     */
    public TodoCommand(String description) {
        this.description = description.trim();
    }

    /**
     * Returns the description of the todo task.
     *
     * @return the description of the todo task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Creates a todo task and adds it to the todo list.
     *
     * @param todoList  the list of tasks.
     * @param ui        the user interface object used to interact with the user.
     * @param fileSaver the FileSaver object used to write data to a file.
     * @throws CowExceptions if any exceptions arise from the implementation.
     */
    @Override
    public void execute(TodoList todoList, Ui ui, FileSaver fileSaver) throws CowExceptions {
        Task t = new Todo(this.description);
        todoList.add(t);
        ui.printAddedTask(t, todoList);
        fileSaver.saveData(todoList);
    }

    /**
     * Checks if this TodoCommand is equal to another object.
     *
     * @param obj the object to compare with.
     * @return true if the given object is equal to this TodoCommand, false otherwise.
     */
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
