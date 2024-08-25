package lama.command;

import lama.LamaException;
import lama.Storage;
import lama.TaskList;
import lama.Ui;

public abstract class Command {
    public abstract void run(TaskList taskList, Storage storage, Ui ui) throws LamaException;

    public boolean isExit() {
        return false;
    }

}
