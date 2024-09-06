package Commands;

import Exceptions.KieTwoForOneException;
import Storage.Storage;
import Tasks.TaskList;
import UI.UI;

public abstract class Command {

    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws KieTwoForOneException;

    public abstract boolean isExit();

}
