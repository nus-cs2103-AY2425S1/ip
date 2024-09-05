package rizzler.command;

import rizzler.Storage;
import rizzler.task.TaskLog;

public class HelpCommand extends Command {

    public HelpCommand() {
        super();
    }

    @Override
    public String[] execute(Storage storage, TaskLog taskLog) {
        return new String[] {"sorry, this command is not available yet."};
    }

}
