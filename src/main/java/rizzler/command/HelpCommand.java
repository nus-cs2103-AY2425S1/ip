package rizzler.command;

import rizzler.Storage;
import rizzler.task.TaskLog;
import rizzler.ui.RizzlerSpeech;

public class HelpCommand extends Command {

    public HelpCommand() {
        super();
    }

    @Override
    public void execute(RizzlerSpeech speech, Storage storage, TaskLog taskLog) {
        speech.say();
        speech.say("T");
        speech.say();
    }

}
