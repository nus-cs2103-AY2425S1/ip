package rizzler.command;

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
