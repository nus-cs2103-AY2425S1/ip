package rizzler.command;

import rizzler.Storage;
import rizzler.task.TaskLog;
import rizzler.ui.RizzlerSpeech;

public class ByeCommand extends Command {

    public ByeCommand() {
        super();
    }

    @Override
    public void execute(RizzlerSpeech speech, Storage storage, TaskLog taskLog) {
        speech.say();
        speech.say("aight bet, cya.");
        speech.say();
    }
}