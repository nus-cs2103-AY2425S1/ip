package rizzler.command;

import rizzler.Storage;
import rizzler.task.TaskLog;

public class EndCommand extends Command {

    public EndCommand() {
        super();
    }

    @Override
    public String[] execute(Storage storage, TaskLog taskLog) {
        setShouldEnd(true);
        return new String[0];
    }
}
