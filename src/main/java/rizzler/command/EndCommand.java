package rizzler.command;

import rizzler.Storage;
import rizzler.task.TaskLog;
import rizzler.ui.RizzlerSpeech;

public class EndCommand extends Command {

    public EndCommand() {
        super();
    }

    @Override
    public void execute(RizzlerSpeech speech, Storage storage, TaskLog taskLog) {
        setShouldEnd(true);
    }
}
