import java.util.HashMap;

public class TodoCommand extends Command {
    HashMap<String, String> argumentMap;

    public TodoCommand(HashMap<String, String> argumentMap) {
        super("todo");
        this.argumentMap = argumentMap;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui) throws InvalidArgumentException {
        verifyFlags(argumentMap);

        String description = argumentMap.get("description");

        TodoTask newTodo = new TodoTask(description);
        tasks.add(newTodo);
        ui.printAddedTask(newTodo, tasks.size());

        return true;
    }
}
