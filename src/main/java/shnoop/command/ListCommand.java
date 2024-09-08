package shnoop.command;

import shnoop.exceptions.*;
import shnoop.storage.Storage;
import shnoop.tasks.*;
import shnoop.ui.*;

import java.io.IOException;

/**
 * Encapsulates all the relevant actions to be taken when a List Command is issued.
 */
public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.list(tasks.list());
    }
}
