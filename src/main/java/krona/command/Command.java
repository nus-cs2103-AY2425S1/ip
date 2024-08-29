package krona.command;

import krona.task.TaskList;
import krona.ui.Ui;
import krona.storage.Storage;
import krona.exception.KronaException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws KronaException;

    public boolean isExit() {
        return false;
    }
}
