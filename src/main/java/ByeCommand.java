import java.util.HashMap;

public class ByeCommand extends Command {

    public ByeCommand(HashMap<String, String> args) {
        super(args);
    }

    @Override
    public boolean isExitCommand() {
        return true;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        // do nothing
    }

}
