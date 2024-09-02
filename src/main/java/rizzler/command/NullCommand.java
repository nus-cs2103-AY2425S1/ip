package rizzler.command;

import rizzler.Storage;
import rizzler.task.TaskLog;
import rizzler.ui.RizzlerSpeech;

public class NullCommand extends Command {

    public NullCommand(String string) {
        super(string);
    }

    @Override
    public void execute(RizzlerSpeech speech, Storage storage, TaskLog taskLog) {
        speech.say();
        // speech.say("sorry love, there's an error in your command.");
        speech.say(getTextInput());
        speech.say();
    }
}
