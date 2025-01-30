package rizz.command;
import rizz.source.TaskList;

public class ExitCommand extends Command {
    @Override
    public String execute(TaskList tasks) {
        return "Bye Bye!";
    }
}
