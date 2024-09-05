package rizzler.command;

import rizzler.Storage;
import rizzler.task.TaskLog;

public class NullCommand extends Command {

    public NullCommand(String string) {
        super(string);
    }

    @Override
    public String[] execute(Storage storage, TaskLog taskLog) {
        return new String[] {getTextInput()};
    }
}
