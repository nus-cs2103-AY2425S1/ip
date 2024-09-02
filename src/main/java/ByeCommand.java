public class ByeCommand extends Command {
    ByeCommand() {
        super();
    }

    @Override
    public void execute(RizzlerSpeech speech, Storage storage, TaskLog taskLog) {
        speech.say();
        speech.say("aight bet, cya.");
        speech.say();
    }
}