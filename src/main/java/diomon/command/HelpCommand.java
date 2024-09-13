package diomon.command;

import diomon.Storage;
import diomon.TaskList;
import diomon.ui.Ui;

public class HelpCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        setResponse("Commands:\n-TODO\n-DEADLINE\n-EVENT\n-LIST\n-MARK\n-UNMARK\n-BYE\n-HELP");
    }
}
