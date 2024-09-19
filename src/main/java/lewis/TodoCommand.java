package lewis;

public class TodoCommand extends Command {

    private final Todo newTodo;
    TodoCommand(String input) {
        String[] arguments = input.split("todo");
        String todoDescription = arguments[1].trim();
        this.newTodo = new Todo(todoDescription);
    }

    /**
     *
     * @return
     */
    public static String getHelpDescription() {
        return "Creates a todo and enters it into the list.\nUsage: todo <description>";
    }

    public void execute() {
        TaskList.add(newTodo);
        Storage.save();
        Ui.printTask(newTodo);
    }
}
