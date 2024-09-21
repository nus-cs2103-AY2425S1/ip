package shnoop.command;

import java.io.IOException;

import shnoop.storage.Storage;
import shnoop.tasks.TaskList;
import shnoop.ui.Ui;

/**
 * Encapsulates all the relevant actions to be taken when an Exit Command is issued.
 */
public class ExitCommand extends Command {

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.save(tasks);
        } catch (IOException e) {
            return ("Something went wrong when trying to writeToFile: " + e.getMessage());
        } finally {
            return ui.goodbye();
        }
    }
}
