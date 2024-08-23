import java.util.HashMap;

public class ListCommand extends Command {
    HashMap<String, String> argumentMap;

    public ListCommand(HashMap<String, String> argumentMap) {
        this.argumentMap = argumentMap;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui) throws InvalidArgumentException {
        verifyFlags(argumentMap);

        ui.printList(tasks);
        return true;
    }

    @Override
    public void verifyFlags(HashMap<String, String> argumentMap) throws InvalidArgumentException {
        // do nothing
    }
}
