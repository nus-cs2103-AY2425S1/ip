package commands;

import exceptions.InputException;
import storage.Storage;
import storage.TaskList;
import ui.Ui;

import java.io.IOException;

public interface Command {
    void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, InputException;
    boolean isExit();
}
