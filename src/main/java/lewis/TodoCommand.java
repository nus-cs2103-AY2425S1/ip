package lewis;

/**
 * This command tells Lewis to create a new todo,
 * enter it into the tasklist, and save it to file.
 */
public class TodoCommand extends Command {

    /** The todo associated with this command */
    private final Todo newTodo;
    /**
     * Constructor for a TodoCommand
     * @param input a string propagated from the standard input stream
     */
    TodoCommand(String input) {
        /* Important, Lewis shouldn't try to parse a null(not empty) string */
        assert input != null : "Input shouldn't be null";
        String[] arguments = input.split("todo");
        String todoDescription = arguments[1].trim();
        this.newTodo = new Todo(todoDescription);
    }

    /**
     * Returns a description for the user on the usage of this command
     * @return a string description
     */
    public static String getHelpDescription() {
        return "Creates a todo and enters it into the list.\nUsage: todo <description>";
    }

    /**
     * Creates a new todo, saves it to storage, and prints
     * to the user the new todo
     */
    public void execute() {
        TaskList.add(newTodo);
        Storage.save();
        Ui.printTask(newTodo);
    }
}
