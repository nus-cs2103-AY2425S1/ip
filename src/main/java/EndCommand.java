import java.util.HashMap;

public class EndCommand extends Command {
    HashMap<String, String> argumentMap;

    public EndCommand(HashMap<String, String> argumentMap) {
        this.argumentMap = argumentMap;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui) {
        verifyFlags(argumentMap);
        return false;
    }

    @Override
    public void verifyFlags(HashMap<String, String> argumentMap) {
        // Do nothing
    }

}
