package rizzler.command;

import rizzler.Storage;
import rizzler.task.TaskLog;

public class ByeCommand extends Command {

    public ByeCommand() {
        super();
    }

    @Override
    public String[] execute(Storage storage, TaskLog taskLog) {
        return new String[] {"aight bet, cya."};
    }
}
