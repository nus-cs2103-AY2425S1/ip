package command;

import main.Storage;
import main.TaskList;

import java.io.IOException;

public abstract class Command {
    public boolean isExit() {
        return false;
    }

    public abstract void execute(TaskList tasks, Storage storage);
}
