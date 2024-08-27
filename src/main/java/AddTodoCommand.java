/**
 * AddTodoCommand represents the command to add a to-do task to the to-do list
 */
public class AddTodoCommand extends Command {

    /** Prefix used to invoke the add to-do command **/
    public static final String COMMAND_PREFIX = "todo";

    public AddTodoCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute(TodoList list, Ui ui, FileStore store) throws TohruException {
        // Check if no arguments are provided
        if (super.arguments == null) {
            throw new TohruException("Missing argument: Please specify description");
        }

        TodoItem newTodo = new TodoItem(super.arguments);
        list.addItem(newTodo);

        ui.showText("Added todo entry:");
        ui.showText(newTodo.toString());

        ui.showText(String.format("There are now %d total entries", list.getTotal()));

        store.saveTodoList(list.getTodoList());

    }
}
