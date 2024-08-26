import java.util.HashMap;

public class EndCommand extends Command {
    HashMap<String, String> argumentMap;

    public EndCommand(HashMap<String, String> argumentMap) {
        super("bye");
        this.argumentMap = argumentMap;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui) throws InvalidArgumentException {
        verifyFlags(argumentMap);
        return false;
    }

}
