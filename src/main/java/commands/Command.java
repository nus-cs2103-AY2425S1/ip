package commands;

import java.io.IOException;

import data.TaskList;
import data.Storage;
import ui.Ui;

public interface Command {
    boolean execute(TaskList taskList, Ui ui, Storage storage) throws IOException;
}
