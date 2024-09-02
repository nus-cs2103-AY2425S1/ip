public class NullCommand extends Command {

    NullCommand(String string) {
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
