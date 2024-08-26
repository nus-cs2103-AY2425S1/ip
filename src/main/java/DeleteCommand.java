import java.util.HashMap;

public class DeleteCommand extends Command {
    HashMap<String, String> argumentMap;

    public DeleteCommand(HashMap<String, String> argumentMap) {
        super("delete");
        this.argumentMap = argumentMap;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui) throws InvalidArgumentException {
        verifyFlags(argumentMap);

        String index = argumentMap.get("index");
        int deleteIndex = Integer.parseInt(index) - 1;
        if (deleteIndex < 0 || deleteIndex >= tasks.size()) {
            throw new InvalidArgumentException("The index of a task to delete is out of range!");
        }

        Task deletedTask = tasks.pop(deleteIndex);
        ui.printDeleteTask(deletedTask, tasks.size());

        return true;
    }
}
