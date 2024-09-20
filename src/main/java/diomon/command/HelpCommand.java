package diomon.command;

import diomon.Storage;
import diomon.task.TaskList;

public class HelpCommand extends Command{
    @Override
    public void execute(TaskList tasks, Storage storage) {
        setResponse("Commands:\n-TODO\n-DEADLINE\n-EVENT\n-LIST\n-MARK\n-UNMARK\n-BYE\n-HELP");
    }
}
