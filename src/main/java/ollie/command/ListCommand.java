package ollie.command;

import ollie.*;

public class ListCommand extends Command{

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.printResponse(tasks.toString());
    }
}
