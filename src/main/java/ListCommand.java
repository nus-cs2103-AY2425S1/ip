import java.util.HashMap;

public class ListCommand extends Command {

    public ListCommand(HashMap<String, String> args) {
        super(args);
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        tasks.listTasks();
    }
}
