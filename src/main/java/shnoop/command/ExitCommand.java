package shnoop.command;

import shnoop.exceptions.*;
import shnoop.storage.Storage;
import shnoop.tasks.*;
import shnoop.ui.*;

import java.io.IOException;

public class ExitCommand extends Command {

    public ExitCommand() {
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.save(tasks);
        } catch (IOException e) {
            System.out.println("Something went wrong when trying to writeToFile: " + e.getMessage());
        } finally {
            ui.goodbye();
        }
    }
}
