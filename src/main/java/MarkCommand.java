import java.util.HashMap;

public class MarkCommand extends Command {

    public MarkCommand(HashMap<String, String> args) {
        super(args);
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        tasks.markTaskAsDone(getArgs().get("main"));
    }
}
