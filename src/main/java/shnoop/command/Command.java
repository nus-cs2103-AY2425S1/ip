package shnoop.command;

import shnoop.exceptions.*;
import shnoop.storage.Storage;
import shnoop.tasks.*;
import shnoop.ui.*;

import java.io.IOException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, ShnoopException, IncompleteEventOrDeadlineException;

    public boolean isExit() {
        return false;
    }
}