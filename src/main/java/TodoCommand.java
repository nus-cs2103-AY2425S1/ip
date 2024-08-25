import java.util.HashMap;

public class TodoCommand extends Command {
    public TodoCommand(HashMap<String, String> args) {
        super(args);
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        Task todo = new Todo(getArgs().get("main"));
        tasks.addTask(todo);
    }

}
