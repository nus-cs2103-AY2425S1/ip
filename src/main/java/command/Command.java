package command;

import exception.KukiShinobuException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public abstract class Command {
    //TODO: Command always takes in tasks, ui and storage in the event that it needs to modify any of the following
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws KukiShinobuException;

    public boolean isExit() {
        return false;
    }
}
