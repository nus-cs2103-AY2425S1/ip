package yapmeister.commands;

import yapmeister.Storage;
import yapmeister.UI;
import yapmeister.task.TaskList;

public interface Command {
    abstract void execute(TaskList tasks, Storage storage, UI ui) throws Exception;

    abstract Command parse(String input) throws Exception;
}
