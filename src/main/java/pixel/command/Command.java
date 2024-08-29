package pixel.command;

import pixel.Storage;
import pixel.PixelException;
import pixel.Ui;
import pixel.task.TaskList;

public abstract class Command {
    private boolean isExit;

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws PixelException;

    public boolean isExit() {
        return this.isExit;
    }
}
