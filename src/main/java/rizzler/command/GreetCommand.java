package rizzler.command;

import rizzler.Storage;
import rizzler.task.TaskLog;

public class GreetCommand extends Command {

    public GreetCommand() {
        super();
    }

    @Override
    public String[] execute(Storage storage, TaskLog taskLog) {
        return new String[] {"how ya' doin. i'm the rizzler.",
        "how may i be of service to you today?"};
    }
}
