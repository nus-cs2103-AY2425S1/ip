package pixel.command;

import pixel.Storage;
import pixel.PixelException;
import pixel.Ui;
import pixel.task.TaskList;

public class ExitCommand extends Command {
    public ExitCommand() {
        super(true);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws PixelException {
        ui.PixelSays("Bye. Hope to see you again soon!");
        ui.closeUi();
    }
}
