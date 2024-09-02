package rizzler.command;

import rizzler.Storage;
import rizzler.task.TaskLog;
import rizzler.ui.RizzlerSpeech;

public class GreetCommand extends Command {

    public GreetCommand() {
        super();
    }

    @Override
    public void execute(RizzlerSpeech speech, Storage storage, TaskLog taskLog) {
        speech.say();
        speech.say("how ya' doin. i'm the rizzler.");
        speech.say("how may i be of service to you today?");
        speech.say();
    }
}
