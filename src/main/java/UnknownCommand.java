import java.util.HashMap;

public class UnknownCommand extends Command {

    public UnknownCommand(HashMap<String, String> args) {
        super(args);
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        System.out.println("UNKNOWN command");
    }
}
