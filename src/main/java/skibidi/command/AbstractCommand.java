package skibidi.command;

import java.util.Optional;

import skibidi.Storage;
import skibidi.TaskList;
import skibidi.Ui;

public abstract class AbstractCommand {
    public abstract Optional<String> execute(TaskList taskList, Storage storage, Ui ui);
}
