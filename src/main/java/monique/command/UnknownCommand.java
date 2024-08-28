package monique.command;

import monique.exception.MoniqueException;
import monique.exception.UnknownCommandException;
import monique.storage.Storage;
import monique.tasklist.TaskList;
import monique.ui.Ui;

public class UnknownCommand extends Command {
    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MoniqueException {
        throw new UnknownCommandException();
    }

    @Override
    public boolean equals(Object obj) {
        // Check if the object is compared with itself
        if (this == obj) {
            return true;
        }

        // Check if the object is an instance of UnknownCommand
        if (obj instanceof UnknownCommand) {
            return true; // All UnknownCommand instances are considered equal
        }

        // If obj is not an instance of UnknownCommand, return false
        return false;
    }
}
