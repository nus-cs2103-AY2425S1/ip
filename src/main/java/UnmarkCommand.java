import java.util.HashMap;

public class UnmarkCommand extends Command {

    public UnmarkCommand(HashMap<String, String> args) {
        super(args);
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        tasks.markTaskAsNotDone(getArgs().get("main"));
    }
}
