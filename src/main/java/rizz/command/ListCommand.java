package rizz.command;
import rizz.source.TaskList;

public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks) {
        return "Here's your list buddy!\n" + tasks.toString();
    }
}
