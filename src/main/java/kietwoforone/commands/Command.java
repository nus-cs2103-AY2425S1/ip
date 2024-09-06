package kietwoforone.commands;

import kietwoforone.exceptions.KieTwoForOneException;
import kietwoforone.storage.Storage;
import kietwoforone.tasks.TaskList;
import kietwoforone.ui.UI;

public abstract class Command {

    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws KieTwoForOneException;

    public abstract boolean isExit();

}
