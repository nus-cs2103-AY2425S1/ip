import java.util.HashMap;

public class UnmarkCommand extends Command {
    HashMap<String, String> argumentMap;

    public UnmarkCommand(HashMap<String, String> argumentMap) {
        super("unmark");
        this.argumentMap = argumentMap;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui) throws InvalidArgumentException {
        verifyFlags(argumentMap);

        String indexStr = argumentMap.get("index");

        if (!indexStr.matches("^\\d+$")) {
            throw new InvalidArgumentException("Please enter a task number!");
        }
        int index = Integer.parseInt(indexStr) - 1;
        if (index < 0 || index > tasks.size() - 1) {
            throw new InvalidArgumentException("Task number out of range!");
        }

        Task task = tasks.get(index);
        task.unmark();

        ui.printMarked(task);

        return true;
    }
}