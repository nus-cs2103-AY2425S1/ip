import java.util.HashMap;

public class DeleteCommand extends Command {

    public DeleteCommand(HashMap<String, String> args) {
        super(args);
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        tasks.deleteTask(getArgs().get("main"));
    }
}
