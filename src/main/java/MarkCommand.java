import java.util.HashMap;

public class MarkCommand extends Command {
    HashMap<String, String> argumentMap;
    boolean toMark;

    public MarkCommand(HashMap<String, String> argumentMap, boolean toMark) {
        this.argumentMap = argumentMap;
        this.toMark = toMark;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui) throws InvalidArgumentException {
        verifyFlags(argumentMap);

        String indexStr = argumentMap.get("argument");

        if (!indexStr.matches("^\\d+$")) {
            throw new InvalidArgumentException("Please enter a task number!");
        }
        int index = Integer.parseInt(indexStr) - 1;
        if (index < 0 || index > tasks.size() - 1) {
            throw new InvalidArgumentException("Task number out of range!");
        }

        Task task = tasks.get(index);
        if (toMark) {
            task.mark();
        } else {
            task.unmark();
        }

        ui.printMarked(task);

        return true;
    }

    @Override
    public void verifyFlags(HashMap<String, String> argumentMap) throws InvalidArgumentException {
        if (!argumentMap.containsKey("argument")) {
            throw new MissingFlagException("Missing index for mark!");
        }
        if (argumentMap.get("argument").isEmpty()) {
            throw new InvalidArgumentException("The index of a mark cannot be empty!");
        }
    }
}