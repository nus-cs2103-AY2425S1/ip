import java.util.HashMap;

public class TodoCommand extends Command {
    HashMap<String, String> argumentMap;

    public TodoCommand(HashMap<String, String> argumentMap) {
        this.argumentMap = argumentMap;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui) throws InvalidArgumentException {
        verifyFlags(argumentMap);

        String description = argumentMap.get("argument");

        TodoTask newTodo = new TodoTask(description);
        tasks.add(newTodo);
        ui.printAddedTask(newTodo, tasks.size());

        return true;
    }

    @Override
    public void verifyFlags(HashMap<String, String> argumentMap) throws InvalidArgumentException {
        if (!argumentMap.containsKey("argument")) {
            throw new MissingFlagException("Missing description for todo!");
        }
        if (argumentMap.get("argument").isEmpty()) {
            throw new InvalidArgumentException("The description of a todo cannot be empty!");
        }
    }
}
