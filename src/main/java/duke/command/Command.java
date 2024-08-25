package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public interface Command {
    void execute(TaskList tasks, Ui ui, Storage storage) throws IOException;
    boolean isExit();
}