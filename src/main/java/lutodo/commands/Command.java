package lutodo.commands;

import lutodo.tasklist.TaskList;
import lutodo.storage.Storage;

public abstract class Command {

    public abstract void execute(TaskList tasks, Storage storage);

    public abstract boolean isExit();

    // public abstract boolean equals(Command otherCommand);

    @Override
    public boolean equals(Object otherCommand){
        return toString().equals(otherCommand.toString());
    }

    @Override
    public String toString() {
        return "Command";
    }
}
